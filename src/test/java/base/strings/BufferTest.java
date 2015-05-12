package base.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        Buffer buffer = new Buffer("Buffer: ");
        Thread thread1 = new Thread(buffer);
        Thread thread2 = new Thread(buffer);

        thread1.start();
        thread2.start();

        boolean thread1IsAlive = true;
        boolean thread2IsAlive = true;

        do {
            if (thread1IsAlive && !thread1.isAlive()) {
                thread1IsAlive = false;
                System.out.println("Thread 1 is dead.");
            }

            if (thread2IsAlive && !thread2.isAlive()) {
                thread2IsAlive = false;
                System.out.println("Thread 2 is dead.");
            }
        } while (thread1IsAlive || thread2IsAlive);
    }
}
