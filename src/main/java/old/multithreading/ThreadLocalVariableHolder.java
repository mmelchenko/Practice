package old.multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Автоматическое выделение собственной памяти каждому потоку.
 */
class Accessor implements Runnable {
    private final int id;
    public Accessor(int id) {
        this.id = id;
    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    public String toString() {
        return "#" + id + " " + ThreadLocalVariableHolder.get();
    }
}
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);
        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }
    };
    public static void increment() {
        value.set(value.get() + 1);
    }
    public static int get() {
        return value.get();
    }
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);     // Небольшая задеожка
        executorService.shutdownNow(); // Выход из всех объектов Accessor
    }
}
