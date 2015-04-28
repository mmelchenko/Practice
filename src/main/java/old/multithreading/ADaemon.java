package old.multithreading;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("Запускаем ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Выход через InterruptedException");
        } finally {
            System.out.println("Должно выполняться всегда?");
        }
    }
}
