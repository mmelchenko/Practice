package multithreading;

import java.util.concurrent.TimeUnit;

/**
 * Потоки порождаемые демонами, также являются демонами.
 */

class DaemonSpawn implements Runnable {
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws Exception {
        Thread daemon = new Thread(new Daemon());
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("daemon.isDaemon() = " + daemon.isDaemon() + ", ");
        // Даем потокам-демонам завершить процесс запуска:
        TimeUnit.SECONDS.sleep(1);
    }
}