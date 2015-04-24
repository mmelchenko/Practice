package concurrentPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CyclicBarrier demo.
 */
class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    public synchronized int getStrides() {
        return strides;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (BrokenBarrierException e) {
            System.out.println("BrokenBarrierException");
            throw new RuntimeException(e);
        }
    }
    public String toString() {
        return "Horse " + id + " ";
    }
    public String tracks() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            string.append("*");
        }
        string.append(id);
        return string.toString();
    }
}

public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int nHorses, final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder string = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    string.append("="); //  Забор на беговой дорожке
                }
                System.out.println(string);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + " won!");
                        executorService.shutdownNow();
                        return;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(pause);
                    } catch (InterruptedException e) {
                        System.out.println("barrier-action sleep interrupted");
                    }
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            executorService.execute(horse);
        }
    }
    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        if (args.length > 0) {
            int n = new Integer(args[0]);
            nHorses = n > 0 ? n : nHorses;
        }
        if (args.length > 1) {
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }
        new HorseRace(nHorses, pause);
    }
}
