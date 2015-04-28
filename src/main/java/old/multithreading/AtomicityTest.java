package old.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Проверка атомарности операции инкремента.
 */
public class AtomicityTest implements Runnable {
    private int i = 0;
    public int getValue() {
        return i;
    }
    private synchronized void evenIncrement() {
        i++;
        i++;
    }
    public void run() {
        while (true) {
            evenIncrement();
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicityTest atomicityTest = new AtomicityTest();
        executorService.execute(atomicityTest);
        while (true) {
            int value = atomicityTest.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
