package multithreading;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public int getValue() {
        return atomicInteger.get();
    }
    private void evenIncrement() {
        atomicInteger.addAndGet(2);
    }
    public void run() {
        while (true) {
            evenIncrement();
        }
    }
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Отмена");
                System.exit(0);
            }
        }, 5000); // Завершение через 5 секунд
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        executorService.execute(atomicIntegerTest);
        while (true) {
            int value = atomicIntegerTest.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
