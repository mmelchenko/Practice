package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String name, House preferredHouse) {
        this.name = name;
        this.preferredHouse = preferredHouse;
    }
    public String toString() {
        return name + "[" + super.toString() + "]. " + preferredHouse + "\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralf the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals " + animals);
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ObjectOutputStream outputStream1 = new ObjectOutputStream(byteArrayOutputStream1);
        outputStream1.writeObject(animals);
        outputStream1.writeObject(animals);
        // Запись в другой поток
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ObjectOutputStream outputStream2 = new ObjectOutputStream(byteArrayOutputStream2);
        outputStream2.writeObject(animals);
        // Теперь восстанавливаем записанные объекты
        ObjectInputStream inputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream1.toByteArray()));
        ObjectInputStream inputStream2 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream2.toByteArray()));

        List animals1 = (List)inputStream1.readObject();
        List animals2 = (List)inputStream1.readObject();
        List animals3 = (List)inputStream2.readObject();

        System.out.println("animals1 " + animals1);
        System.out.println("animals2 " + animals2);
        System.out.println("animals3 " + animals3);
    }
}
