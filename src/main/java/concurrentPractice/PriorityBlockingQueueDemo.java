package concurrentPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue Demo.
 */
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private Random random = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();
    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }
    public int compareTo(PrioritizedTask arg) {
        return priority < arg.priority ? 1 : (priority > arg.priority ? -1 : 0);
    }
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(this);
    }
    public String toString() {
        return String.format("[%1$-3d]", priority) + " Task " + id;
    }
    public String summary() {
        return "(" + id + ":" + priority + ")";
    }
    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService executorService;
        public EndSentinel(ExecutorService e) {
            super(-1); // Минимальный приоритет в этой программе
            executorService = e;
        }
        public void run() {
            int count = 0;
            for (PrioritizedTask prioritizedTask : sequence) {
                System.out.print(prioritizedTask.summary());
                if (++count % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println(this + " Вызов shutdownNow()");
            executorService.shutdownNow();
        }
    }
}

class PriorityTaskProducer implements Runnable {
    private Random random = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService executorService;
    public PriorityTaskProducer(Queue<Runnable> queue, ExecutorService executorService) {
        this.queue = queue;
        this.executorService = executorService;
    }
    public void run() {
        // Неограниченная очередь без блокировки.
        // Быстрое заполнение случайными приоритетами:
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(random.nextInt(10)));
            Thread.yield();
        }
        // Добавление высокоприоритетных задач:
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            // Добавление заданий, начиная с наименьших приоритетов:
            for (int i = 0; i < 10; i++) {
                queue.add(new PrioritizedTask(i));
            }
            // предохранитель для остановки всех задач:
            queue.add(new PrioritizedTask.EndSentinel(executorService));
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Завершение PriorityTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> queue;
    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Использование текущего потока для запуска задачи
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Завершение PrioritizedTaskConsumer");
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        executorService.execute(new PriorityTaskProducer(queue, executorService));
        executorService.execute(new PrioritizedTaskConsumer(queue));
    }
}
