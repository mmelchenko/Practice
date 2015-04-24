package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Вызов sleep() для остановке потока.
 */
public class SleepingTask extends LiftOff {
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(status());
                // Старый стилью
                // Thread.sleep(100);
                // Стиль Java SE5/6
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            try {
                executorService.execute(new SleepingTask());
            } catch (RejectedExecutionException e) {
                System.err.println("RejectedExecutionException");
            }
            executorService.shutdown();
        }
    }
}
