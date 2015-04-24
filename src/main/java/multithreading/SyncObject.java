package multithreading;

/**
 * Синхронизация по другому обьекту.
 */
class DualSynch {
    private Object syncObject = new Object();
    public synchronized void first() {
        for (int i = 0; i < 5; i++) {
            System.out.println("first()");
            Thread.yield();
        }
    }
    public void second() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("second()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch dualSynch = new DualSynch();
        new Thread() {
            public void run() {
                dualSynch.first();
            }
        }.start();
        dualSynch.second();
    }
}
