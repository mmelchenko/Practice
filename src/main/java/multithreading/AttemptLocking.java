package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Объекты Lock из библиотеки concurrent делают возможными
 * попытки установить блокировку в течение некоторого времени.
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }
    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        final AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.untimed(); // True -- блокировка доступна
        attemptLocking.timed();   // True -- блокировка доступна
        // Теперь создаем отдельную задачу для установления блокировки
        new Thread() {
            {
                setDaemon(true);
            }
            public void run() {
                attemptLocking.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();            // Даем возможность 2-й задаче
        attemptLocking.untimed();  // False -- блокировка захвачена задачей
        attemptLocking.timed();    // False -- блокировка захвачена задачей
    }
}
