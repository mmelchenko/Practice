package old.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Кажущиеся безопасными операции с появлением потоков перестают быть таковыми.
 */

// Повторное использование памяти предохраняет программу от ее нехватки
class CircularSet {
    private int[] array;
    private int length;
    private int index = 0;
    public CircularSet(int size) {
        array = new int[size];
        length = size;
        // Инициализируем значением, которое не производится
        // классом SerialNumberGenerator
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }
    public synchronized void add(int i) {
        array[index] = i;
        // Возврат индекса к началу с записью поверх старых значений:
        index = ++index % length;
    }
    public synchronized boolean contains(int value) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
}

public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Дупликат: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
        public static void main(String[] args) throws Exception {
            for (int i = 0; i < SIZE; i++) {
                executorService.execute(new SerialChecker());
            }
            // Остановиться после n секунд при наличии аргумента
            if (args.length > 0) {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
                System.out.println("Дупликатов не обнаружено");
                System.exit(0);
            }
        }
    }
}
