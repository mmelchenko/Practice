package multithreading;

import java.util.concurrent.TimeUnit;

/**
 * Потоки-демоны не выполняют секцию finally.
 */

public class DaemonsDontRunFinally {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new ADaemon());
        thread.setDaemon(true);
        thread.start();
    }
}