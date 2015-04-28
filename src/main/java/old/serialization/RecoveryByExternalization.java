package old.serialization;

import java.io.*;

/**
 * Восстановление объекта Extenalizable.
 */

class Blip3 implements Externalizable {
    private int i;
    private String s;
    public Blip3() {
        System.out.println("Конструктор Blip3");
        // s, i  не инициализируются
    }
    public Blip3(String s, int i) {
        System.out.println("Blip3(String s, int i)");
        this.s = s;
        this.i = i;
        //s и i  инициализируются только вне конструктора по умолчанию
    }
    public String toString() {
        return s + i;
    }
    public void writeExternal(ObjectOutput output) throws IOException {
        System.out.println("Blip3.writeExternal");
        output.writeObject(s);
        output.writeInt(i);
    }
    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        s = (String)input.readObject();
        i = input.readInt();
    }
}

public class RecoveryByExternalization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Создание объектов: ");
        Blip3 blip3 = new Blip3("Строка ", 15);
        System.out.println(blip3);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
        System.out.println("Сохранение объекта: ");
        outputStream.writeObject(blip3);
        outputStream.close();
        // Теперь восстанавливаем объект:
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Blip3.out"));
        System.out.println("Восстановление blip3: ");
        blip3 = (Blip3)inputStream.readObject();
        System.out.println(blip3);
    }
}
