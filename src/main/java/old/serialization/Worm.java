package old.serialization;

import java.io.*;
import java.util.Random;

/**
 * Сериализация объекта.
 */
class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char count;
    public Worm(int numberOfSegments, char x) {
        System.out.println("Конструктор Worm " + numberOfSegments);
        count = x;
        if(--numberOfSegments > 0) {
            next = new Worm(numberOfSegments, (char)(count + 1));
        }
    }
    public Worm() {
        System.out.println("Конструктор по умолчанию");
    }
    public String toString() {
        StringBuilder result = new StringBuilder("*");
        result.append(count);
        result.append("(");
        for(Data data : d) {
            result.append(data);
        }
        result.append(")");
        if(next != null) {
            result.append(next);
        }
        return result.toString();
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Worm worm1 = new Worm(6, 'a');
        System.out.println("червяк1 = " + worm1);
        ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out1.writeObject("Хранилище червяков\n");
        out1.writeObject(worm1);
        out1.close(); //
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();
        Worm worm2 = (Worm)in.readObject();
        System.out.println(s + "червяк2 = " + worm2);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(byteArray);
        out2.writeObject("Память обьекта Worm\n");
        out2.writeObject(worm1);
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(byteArray.toByteArray()));
        s = (String)in2.readObject();
        Worm worm3 = (Worm)in2.readObject();
        System.out.println(s + "червяк3 = " + worm3);
    }
}
