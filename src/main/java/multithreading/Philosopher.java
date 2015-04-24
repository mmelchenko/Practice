package multithreading;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Обедающий философ.
 */
public class Philosopher implements Runnable {
    private ChopStick left;
    private ChopStick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);
    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }
    public Philosopher(ChopStick left, ChopStick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }
    public void run() {
        try {
            System.out.println(this + " думает");
            pause();
            // Философ проголодался
            System.out.println(this + " берет правую палочку");
            right.take();
            System.out.println(this + " берет левую палочку");
            left.take();
            System.out.println(this + " ест");
            pause();
            right.drop();
            left.drop();
        } catch (InterruptedException e) {
            System.out.println(this + " выход через прерывание");
        }
    }
    public String toString() {
        return "Философ " + id;
    }
}
