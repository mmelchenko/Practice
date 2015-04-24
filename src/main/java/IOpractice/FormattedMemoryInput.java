package IOpractice;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Форматированное чтение из памяти.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedReading.read("FormattedMemoryInput.java").getBytes()));
            while(true) {
                System.out.print((char)in.readByte());
            }
        } catch(EOFException e) {
            System.err.println("End of stream");
        }
    }
}
