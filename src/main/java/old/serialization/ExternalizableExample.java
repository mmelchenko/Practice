package old.serialization;

import java.io.*;

/**
 * Простая реализация интерфейса Externalizable (с проблемами).
 */
class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Конструктор Blip1");
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    Blip2() {
        System.out.println("Конструктор Blip2");
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}

public class ExternalizableExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Создание объектов: ");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("Сохранение объектов: ");
        objectOutputStream.writeObject(blip1);
        objectOutputStream.writeObject(blip2);
        objectOutputStream.close();
        // Восстановление сохраненных обьектов
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Восстановление blip1: ");
        blip1 = (Blip1)objectInputStream.readObject();
        // А вот и проблемы...
        //! System.out.println("Восстановление blip2: ");
        //! blip2 = (Blip2)objectInputStream.readObject();
        // для успешного восстановления объекта blip2  необходимо сделать констуктор класса Blip2 публичным
    }
}
