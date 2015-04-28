package old.multithreading;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Реализация тепличного котроллера с использованием ScheduledThreadPoolExecutor.
 */
public class GreenHouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "День";
    public synchronized String getThermostat() {
        return thermostat;
    }
    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }
    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }
    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }
    class LightOn implements Runnable {
        @Override
        public void run() {
            // Сюда помещается аппаратный вызов, выполняющий
            // физическое включение света
            System.out.println("Свет включен");
            light = true;
        }
    }
    class LightOff implements Runnable {
        @Override
        public void run() {
            // Сюда помещается аппаратный вызов, выполняющий
            // физическое выключение света
            System.out.println("Свет выключен");
            light = false;
        }
    }
    class WaterOn implements Runnable {
        @Override
        public void run() {
            // Здесь размещается код включения
            // системы полива.
            System.out.println("Полив включен");
            water = true;
        }
    }
    class WaterOff implements Runnable {
        @Override
        public void run() {
            // Здесь размещается код выключения
            // системы полива.
            System.out.println("Полив выключен");
            water = false;
        }
    }
    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            // Здесь размещается код управления оборудованием
            System.out.println("Включение ночного режима");
            setThermostat("Ночь");
        }
    }
    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            // Здесь размещается код управления оборудованием
            System.out.println("Включение дневного режима");
            setThermostat("День");
        }
    }
    class Bell implements Runnable {
        public void run() {
            System.out.println("Бам!");
        }
    }
    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("Завершение");
            scheduler.shutdownNow();
            // Для выполнения этой операции необходимо запустить
            // отдельную задачу, так как планировщик был отключен
            new Thread() {
                @Override
                public void run() {
                    for(DataPoint dataPoint : data) {
                        System.out.println(dataPoint);
                    }
                }
            }.start();
        }
    }
    // Коллекция данных:
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint(Calendar time, float temperature, float humidity) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }
        public String toString() {
            return time.getTime() + String.format(" температура: %1$.1f влажность: %2$.2f", temperature, humidity);
        }
    }
    private Calendar lastTime = Calendar.getInstance();
    {
        // Регулировка даты до получаса
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }
    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random random = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
    class CollectData implements Runnable {
        @Override
        public void run() {
            System.out.println("Сбор данных");
            synchronized (GreenHouseScheduler.this) {
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                // С вероятностью 1/5 происходит смена направления:
                if (random.nextInt(5) == 4) {
                    tempDirection = -tempDirection;
                }
                // Сохранить предидущее значение:
                lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
                if (random.nextInt(5) == 4) {
                    humidityDirection = -humidityDirection;
                }
                lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
                // Объект Calendar необходимо клонировать, иначе
                // все DataPoint будут содержать ссылки
                // на одно и то же lastTime.
                // Для базового объекта, такого как Calendar -
                // вызова clone() вполне достаточно.
                data.add(new DataPoint((Calendar)lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }
    public static void main(String[] args) {
        GreenHouseScheduler greenHouseScheduler = new GreenHouseScheduler();
        greenHouseScheduler.schedule(greenHouseScheduler.new Terminate(), 5000);
        greenHouseScheduler.repeat(greenHouseScheduler.new Bell(), 0, 1000);
        greenHouseScheduler.repeat(greenHouseScheduler.new ThermostatNight(), 0, 2000);
        greenHouseScheduler.repeat(greenHouseScheduler.new LightOn(), 0, 200);
        greenHouseScheduler.repeat(greenHouseScheduler.new LightOff(), 200, 400);
        greenHouseScheduler.repeat(greenHouseScheduler.new WaterOn(), 0, 600);
        greenHouseScheduler.repeat(greenHouseScheduler.new WaterOff(), 0, 800);
        greenHouseScheduler.repeat(greenHouseScheduler.new ThermostatDay(), 0, 1400);
        greenHouseScheduler.repeat(greenHouseScheduler.new CollectData(), 500, 500);
    }
}
