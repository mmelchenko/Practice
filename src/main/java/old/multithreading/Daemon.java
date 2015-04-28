package old.multithreading;

/**
 * Created by dtv on 23.03.15.
 */
public class Daemon implements Runnable {
    private Thread[] threads = new Thread[10];
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println("DaemonSpan " + i + " started. ");
        }
        for (int i = 0; i < threads.length; i++) {
            System.out.println("threads[" + i + "].isDaemon() = " + threads[i].isDaemon() + ", ");
        }
        while (true) {
            Thread.yield();
        }
    }
}
