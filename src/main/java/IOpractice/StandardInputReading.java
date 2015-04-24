package IOpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Чтение из стандартного потока ввода.
 */
public class StandardInputReading {
    public static void main(String[] args) throws IOException{
        BufferedReader standardReading = new BufferedReader(new InputStreamReader(System.in));
        String string;
        while ((string = standardReading.readLine()) != null && string.length() != 0) {
            System.out.println(string);
            //Пустая строка завершает программу
        }
    }
}
