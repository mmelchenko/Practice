package old.serialization;

import java.io.*;

/**
 * Управление сериализацией с определением собственных
 * методов writeObject() и readObject().
 */
public class SerializationControl implements Serializable {
    private String first;
    private transient String second;
    public SerializationControl(String first, String second) {
        this.first = "Не объявлено transient: " + first;
        this.second = "Объявлено transient: "  + second;
    }
    public String toString() {
        return first + "\n" + second;
    }
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(second);
    }
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        second = (String)stream.readObject();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializationControl serializationControl = new SerializationControl("Test1", "Test2");
        System.out.println("Перед записью \n" + serializationControl);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(serializationControl);
        // Теперь восстанавливаем...
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        SerializationControl serializationControl2 = (SerializationControl)inputStream.readObject();
        System.out.println("После восстановления:\n" + serializationControl2);
    }
}
