package base.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by dtv on 05.05.2015.
 */
public class BuilderTest {

    @Test
    public void builderTest() {
        Builder builder = new Builder("Helpful");
        assertEquals(builder.getStringBuilder().indexOf("l"), 2);
        assertEquals(builder.getStringBuilder().indexOf("l", 3), 6);
        assertEquals(builder.getStringBuilder().lastIndexOf("l"), 6);
        builder.getStringBuilder().replace(0, 4, "Mind");
        assertEquals(builder.getStringBuilder().toString(), "Mindful");
        assertEquals(builder.getStringBuilder().charAt(4), 'f');
    }

    @Test
    public void testMultithreadingBuilder() throws InterruptedException {
        Builder builder = new Builder("", 1000);

        Thread thread1 = new Thread(builder);
        Thread thread2 = new Thread(builder);
        Thread thread3 = new Thread(builder);
        Thread thread4 = new Thread(builder);
        Thread thread5 = new Thread(builder);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        String[] iterations = builder.getStringBuilder().toString().split(";");
        System.out.println(builder.getStringBuilder().toString());
        assertNotEquals(iterations.length, builder.getIterations());
    }
}
