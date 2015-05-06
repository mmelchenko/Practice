package old.IOpractice;


import org.junit.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BufferedReadingTest {
    File tst;
    String check = "check me in file";


    //    @Before
    public void init() {
        tst = new File("B.txt");
        try {
            new FileWriter(tst).append(check);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @After
    public void clear() {
        if (tst != null)
            tst.delete();
    }

    //    @Test
    public void testRead() throws Exception {
        Assert.assertEquals(BufferedReading.read(tst.getAbsoluteFile().getName()), check);
    }

    //    @Test(expected = IOException.class)
    public void testReadNegative() throws Exception {
        BufferedReading.read(null);
    }

    //    @Test
    public void testReadFromFile() throws Exception {
        int len = 4;
        assertEquals(new String(BufferedReading.readFromFile(len, new File("A.txt")), "UTF-8"), check.substring(check.length()-len));
    }


}