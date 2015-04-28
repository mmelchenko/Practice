package old.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Простейшее взаимодействие задач.
 * Нанесение пасты на машину и последующая шлифовка.
 */
class Car {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true; // Готово к обработке
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false; // Готово к нанесению очередного слоя
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false) {
            wait();
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car car) {
        this.car = car;
    }
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Наносим пасту!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Текущее действие прервано");
        }
        System.out.println("Заканчиваем наносить пасту.");
    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car car) {
        this.car = car;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Полируем!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Текущее действие прервано");
        }
        System.out.println("Заканчиваем полировку.");
    }
}
public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);  //  Небольшая задержка
        executorService.shutdownNow();  // Прерывание всех задач
    }
}
