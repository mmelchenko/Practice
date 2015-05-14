package base.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by dtv on 05.05.2015.
 */
public class BufferTest {

    @Test
    public void bufferTest() {
        Buffer buffer = new Buffer("Face");
        buffer.getStringBuffer().append("less");
        assertEquals(buffer.getStringBuffer().toString(), "Faceless");
        buffer.getStringBuffer().append("Fear", 0, 2);
        assertEquals(buffer.getStringBuffer().toString(), "FacelessFe");
        buffer.getStringBuffer().deleteCharAt(buffer.getStringBuffer().length() - 1);
        assertEquals(buffer.getStringBuffer().toString(), "FacelessF");
        buffer.getStringBuffer().delete(0, 4);
        assertEquals(buffer.getStringBuffer().toString(), "lessF");
        buffer.setStringBuffer(new StringBuffer(buffer.getStringBuffer().subSequence(0, 4)));
        assertEquals(buffer.getStringBuffer().toString(), "less");
        buffer.getStringBuffer().insert(0, "Fear");
        assertEquals(buffer.getStringBuffer().toString(), "Fearless");
        buffer.setStringBuffer(new StringBuffer("!!!SSENDAM"));
        buffer.getStringBuffer().reverse();
        assertEquals(buffer.getStringBuffer().toString(), "MADNESS!!!");
    }

    @Test
    public void testMultithreadingBuffer() {
        Buffer buffer = new Buffer("", 1000);

        Thread thread1 = new Thread(buffer);
        Thread thread2 = new Thread(buffer);
        Thread thread3 = new Thread(buffer);
        Thread thread4 = new Thread(buffer);
        Thread thread5 = new Thread(buffer);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }


        String[] iterations = buffer.getStringBuffer().toString().split(";");
        System.out.println(buffer.getStringBuffer().toString());
        assertNotEquals(iterations.length, buffer.getIterations());
    }
}
