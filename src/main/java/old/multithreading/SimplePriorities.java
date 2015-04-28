package old.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

;

/**
 * Использование приоритетов потоков.
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;  // Откючение оптимизации
    private int priority;
    public SimplePriorities(int priority) {
        this.priority = priority;
    }
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // Высокозатратная, прерываемая операция:
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E)/(double)i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            try {
                executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
                executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
            } catch (RejectedExecutionException e) {
                System.out.println(e);
            }
            executorService.shutdown();
        }
    }
}
