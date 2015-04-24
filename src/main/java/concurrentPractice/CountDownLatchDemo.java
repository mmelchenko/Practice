package concurrentPractice;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch demo.
 */
// Часть основной задачи
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;
    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println("Задача прервана ");
        }
    }
    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " завершается");
    }
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

// Ожидание по объекту CountDownLatch
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            latch.await();
            System.out.println("Барьер пройден для " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " прервано");
        }
    }
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Все подзадачи совместо используют один обьект CountDownLatch
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(latch));
        }
        System.out.println("Запущенны все задачи");
        executorService.shutdown();  // Выход после завершения все задач
    }
}
