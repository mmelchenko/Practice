package IOpractice;

import java.io.IOException;
import java.io.StringReader;

/**
 * Чтение из памяти
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader(BufferedReading.read("MemoryInput.java"));
        int c;
        while((c = stringReader.read()) != -1) {
            System.out.print((char)c);
        }
    }
}
