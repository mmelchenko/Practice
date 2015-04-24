package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Синхронизация блоков вместо целых методов. Также демонстрирует защиту
 * неприспособленого к многопоточности класса другим классом.
 */
class Pair {
    // Потоко не безопасно
    private int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pair() {
        this(0, 0);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void incrementX() {
        x++;
    }
    public void incrementY() {
        y++;
    }
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
    public class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }
    // Произвальний вариант - обе переменные должны быть равны:
    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}

// Защита класса Pair внутри приспособленного к потокам класса:
abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair pair = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair() {
        // Создаем копию, чтобы сохранить оригинал
        return new Pair(pair.getX(), pair.getY());
    }
    // Предпологается, что операция занимает некоторое время
    protected void store(Pair pair) {
        storage.add(pair);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {}
    }
    public abstract void increment();
}

// Синхронизация всего метода
class PairManager1 extends PairManager {
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());
    }
}

// Использование критической секции
class PairManager2 extends PairManager {
    public void increment() {
        Pair temp;
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

class PairManipulator implements Runnable {
    private PairManager pairManager;
    public PairManipulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }
    public void run() {
        while (true) {
            pairManager.increment();
        }
    }
    public String toString() {
        return "Pair: " + pairManager.getPair() + " checkCounter = " + pairManager.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pairManager;
    public PairChecker(PairManager pairManager) {
        this.pairManager = pairManager;
    }
    public void run() {
        while (true) {
            pairManager.checkCounter.incrementAndGet();
            pairManager.getPair().checkState();
        }
    }
}

public class CriticalSection {
    // Сравнение двух подходов
    static void testApproaches(PairManager pairManager1, PairManager pairManager2) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        PairManipulator pairManipulator1 = new PairManipulator(pairManager1);
        PairManipulator pairManipulator2 = new PairManipulator(pairManager2);

        PairChecker pairChecker1 = new PairChecker(pairManager1);
        PairChecker pairChecker2 = new PairChecker(pairManager2);

        executorService.execute(pairManipulator1);
        executorService.execute(pairManipulator2);

        executorService.execute(pairChecker1);
        executorService.execute(pairChecker2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Сон прерван");
        }
        System.out.println("pairManipulator1 " + pairManipulator1 + "\npairManipulator2: " + pairManipulator2);
        System.exit(0);
    }
    public static void main(String[] args) {
        PairManager pairManager1 = new PairManager1();
        PairManager pairManager2 = new PairManager2();
        testApproaches(pairManager1, pairManager2);
    }
}
