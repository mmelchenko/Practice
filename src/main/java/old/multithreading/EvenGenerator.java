package old.multithreading;

import java.util.concurrent.RejectedExecutionException;

/**
 * Конфликт потоков.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public int next() {
        ++currentEvenValue;   // Опасная точка!
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args) {
        try {
            EvenChecker.test(new EvenGenerator());
        } catch (RejectedExecutionException e) {
            System.out.println("RejectedExecutionException found!");
        }
    }
}
