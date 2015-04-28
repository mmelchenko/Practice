package old;

import java.util.List;
import java.util.concurrent.*;

/**
 * Exchanger Demo.
 */
class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchanger, Generator<T> generator, List<T> holder) {
        this.exchanger = exchanger;
        this.generator = generator;
        this.holder = holder;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                // Заполненный контейнер заменяется пустым:
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder)  {
        this.exchanger = exchanger;
        this.holder = holder;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x;
                    holder.remove(x);  //  Приемлемо для CopyOnWriteArrayList
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("" + value);
    }
}

public class ExchangerDemo {
    static int size = 10;
    static int delay = 5; // Секунды
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            size = new Integer(args[0]);
        }
        if (args.length > 1) {
            delay = new Integer(args[1]);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> exchanger = new Exchanger<List<Fat>>();
        List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
        List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();
        executorService.execute(new ExchangerProducer<Fat>(exchanger, BasicGenerator.create(Fat.class), producerList));
        executorService.execute(new ExchangerConsumer<Fat>(exchanger, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        executorService.shutdownNow();
    }
}
