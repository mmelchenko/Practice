package IOpractice;

import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class BufferedReadingTest {
    File tst;
    String check = "check me in file";

    @Before
    public void init() {
        tst = new File("B.txt");
        try {
            new FileWriter(tst).append(check);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clear() {
        if (tst != null)
            tst.delete();
    }

    @Test
    public void testRead() throws Exception {
    }

    @Test
    public void testReadFromFile() throws Exception {
        assertEquals(new String(BufferedReading.readFromFile(4, new File("A.txt")), "UTF-8"), "sed.");
    }


}