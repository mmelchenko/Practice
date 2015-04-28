package old.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * Потоки-демоны не препятствуют завершению работы программы.
 */
public class SimpleDemons implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDemons());
            daemon.setDaemon(true);  // Необходимо вызвать перед start()
            daemon.start();
        }
        System.out.println("Все демоны запущены");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
