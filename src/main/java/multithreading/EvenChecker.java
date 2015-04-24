package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }
    public void run() {
        while (!generator.isCanceled()) {
            int next = generator.next();
            if (next % 2 != 0) {
                System.out.println(next + " не четно!");
                generator.cancel(); // Отмена всех EvenChecker
            }
        }
    }
    // IntGenerator
    public static void test(IntGenerator generator, int count) {
        System.out.println("Нажмите Control-C, чтобы завершить программу");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(generator, i));
            executorService.shutdown();
        }
    }
    // Значение по умолчанию для count:
    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
