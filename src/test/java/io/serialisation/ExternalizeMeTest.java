package io.serialisation;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExternalizeMeTest {

    static List<String> list = new ArrayList<String>();
    static String expectedResult = "[A, B, C, D, E]";
    static ExternalizeMe me;
    File file = new File("ext.out");

    @BeforeClass
    public static void initialization() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        me = new ExternalizeMe(list);
    }

    @Test
    public void testWriteExternal() throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(me);
        oos.flush();
        oos.close();
    }

    @Test
    public void testReadExternal() throws Exception {
        ExternalizeMe externalizeMe;
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        externalizeMe = (ExternalizeMe) ois.readObject();
        ois.close();

        assertEquals(externalizeMe, expectedResult);
    }

}