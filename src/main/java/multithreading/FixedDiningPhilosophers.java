package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Обедающие философы без взаимной блокировки.
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        ChopStick[] sticks = new ChopStick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new ChopStick();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                executorService.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
            } else {
                executorService.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
            }
        }
        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Нажмите 'Enter', чтобы завершить работу");
            System.in.read();
        }
        executorService.shutdownNow();
    }
}
