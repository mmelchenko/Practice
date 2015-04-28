package old.serialization;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Ключевое слово "transient".
 */
public class Transient implements Serializable {
    private Date date = new Date();
    private String userName;
    private transient String password;
    public Transient(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String toString() {
        return "информация о сеансе. \n пользователь " + userName + "\n дата " + date + "\n пароль " + password;
    }
    public static void main(String[] args) throws Exception {
        Transient a = new Transient("User", "Password");
        System.out.println("Сеанс a = " + a);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Transient.out"));
        outputStream.writeObject(a);
        outputStream.close();
        TimeUnit.SECONDS.sleep(1);
        // Восстановление
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Transient.out"));
        System.out.println("Восстановление объекта. Текущее время: " + new Date());
        a = (Transient)inputStream.readObject();
        System.out.println("Сеанс а = " + a);
    }
}
