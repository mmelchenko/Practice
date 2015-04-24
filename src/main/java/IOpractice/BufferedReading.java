package IOpractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Буферизированное чтение из файла
 */
public class BufferedReading {

    public static String read(String fileName) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        while ((string = in.readLine()) != null) {
            stringBuilder.append(string + "\n");
        }
        in.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read("BufferedReading.java"));
    }
}
