package old;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Пример использования очередей и многопоточного программирования.
 */

// Объекты, доступные только для чтения, не требуют синхронизации
class Customer {
    private final int serviceTime;
    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

// Очередь клиентов умеет выводить информацию о своем состоянии:
class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }
    public String toString() {
        if (this.size() == 0) {
            return "[Пусто]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}

// Случайное добавление клиентов в очередь:
class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random random = new Random(47);
    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        } catch(InterruptedException e) {
            System.out.println("CustomerGenerator прерван");
        }
        System.out.println("CustomerGenerator исполняется");
    }
}

class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;
    // Счетчик клиентов, обслуженных за текущую смену:
    private int customersServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;
    public Teller(CustomerLine customers) {
        this.customers = customers;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customersServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "прерван");
        }
        System.out.println(this + "завершается");
    }
    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }
    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine:"уже обслуживает: " + this;
        servingCustomerLine = true;
    }
    public String toString() {
        return "Кассир " + id + " ";
    }
    public String shortString() {
        return "К" + id;
    }
    // Используется приоритетной очередью:
    public synchronized int compareTo(Teller other) {
        return customersServed < other.customersServed ? -1 : (customersServed == other.customersServed ? 0 : 1);
    }
}

class TellerManager implements Runnable {
    private ExecutorService executorService;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;
    public static Random random = new Random(47);
    public TellerManager(ExecutorService executorService, CustomerLine customers, int adjustmentPeriod) {
        this.executorService = executorService;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // Начинаем с одного кассира:
        Teller teller = new Teller(customers);
        executorService.execute(teller);
        workingTellers.add(teller);
    }
    public void adjustTellerNumber() {
        // Фактически это система управления. Регулировка числовых
        // параметров позволяет выявить проблемы стабильности
        // в механизме управления.
        // Если очередь слишком длинна, добавить другого кассира:
        if (customers.size() / workingTellers.size() > 2) {
            // Если кассиры отдыхают или заняты другими делами,
            // вернуть одного из них:
            if (tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            // Иначе создаем (нанимаем) нового кассира
            Teller teller = new Teller(customers);
            executorService.execute(teller);
            workingTellers.add(teller);
            return;
        }
        // Если очередь достаточно коротка, освободить кассира:
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        // Если очереди нет, достаточного кассира:
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }
    // Поручаем кассиру другую работу или отправляем его отдыхать:
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "прерван");
        }
        System.out.println(this + "завершается");
    }
    public String toString() {
        return "TellerManager: ";
    }
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Если очередь слишком длинна, клиенты уходят:
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        executorService.execute(new CustomerGenerator(customers));
        // TellerManager добавляет и убирает кассиров
        // по мере необходимости:
        executorService.execute(new TellerManager(executorService, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) {  // Необязательный аргумент
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        } else {
            System.out.println("Нажмите 'Enter' для выхода");
            System.in.read();
        }
        executorService.shutdownNow();
    }
}
