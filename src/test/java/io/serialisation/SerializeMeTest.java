package io.serialisation;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class SerializeMeTest {

    @Test
    public void testSerialize() throws Exception {
//        проверить
        FileOutputStream fos = new FileOutputStream("ext2.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        SerializeMe serializeMe = new SerializeMe(oos, "new line");
        ObjectOutputStream stream = (ObjectOutputStream) serializeMe.getOs();
        stream.writeObject(serializeMe);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("ext2.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializeMe serializeMe1 = (SerializeMe) ois.readObject();

        assertEquals(serializeMe1.getS(), "new line");
        assertEquals(serializeMe1.getOs(), null);
    }

}