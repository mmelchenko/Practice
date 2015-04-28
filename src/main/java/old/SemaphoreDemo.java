package old;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Тестирование класса Pool.
 */

// Задача для получения ресурса из пула:
class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;
    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checked in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

public class SemaphoreDemo {
    final static int SIZE = 25;
    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<Fat>();
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkOut();
            System.out.println(i + " main() thread checked out ");
            fat.operation();
            list.add(fat);
        }
        Future<?> blocked = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Семафор предотвращает лишний вызов checkOut,
                    // поэтому следующий вызов блокируется:
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true); // Выход из заблокированого вызова
        System.out.println("Checking in objects in " + list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat fat : list) {
            pool.checkIn(fat); // Второй вызов  checkIn игнорируется
        }
        executorService.shutdown();
    }
}
