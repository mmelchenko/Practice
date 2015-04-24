package concurrentPractice;

/**
 * Created by dtv on 02.04.15.
 */
import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

class DelayedTask1 implements Runnable, Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask1> sequence =
            new ArrayList<DelayedTask1>();
    public DelayedTask1(int delayInMilliseconds) {

        delta = delayInMilliseconds;
        trigger = System.nanoTime() +
                NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);

    }
    public long getDelay(TimeUnit unit) {

        return unit.convert(
                trigger - System.nanoTime(), NANOSECONDS);

    }
    public int compareTo(Delayed arg) {

        DelayedTask1 that = (DelayedTask1)arg;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;

    }
    public void run() {
        //printnb(this + " ");
    }
    public String toString() {

        return String.format("[%1$-4d]", delta) +
                " Task " + id;

    }
    public String summary() {

        return "(" + id + ":" + delta + ")";

    }
    public static class EndSentinel extends DelayedTask1 {

        private ExecutorService exec;
        public EndSentinel(int delay, ExecutorService e) {

            super(delay);
            exec = e;

        }
        public void run() {

            for(DelayedTask1 pt : sequence) {

                //printnb(pt.summary() + " ");

            }
            //print();
            //print(this + " Calling shutdownNow()");
            exec.shutdownNow();

        }

    }

}

class DelayedTaskConsumer1 implements Runnable {

    private DelayQueue<DelayedTask1> q;
    public DelayedTaskConsumer1(DelayQueue<DelayedTask1> q) {

        this.q = q;

    }
    public void run() {

        try {

            while(!Thread.interrupted())
                q.take().run(); // Run task with the current thread

        } catch(InterruptedException e) {

            // Acceptable way to exit

        }
        //print("Finished DelayedTaskConsumer");

    }

}

