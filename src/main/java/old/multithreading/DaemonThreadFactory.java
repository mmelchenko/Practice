package old.multithreading;

import java.util.concurrent.ThreadFactory;

/**
 * Фабрика потоков-демонов.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
