package base.strings;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    public void testMultithreadingBuilder() {
        Builder builder = new Builder("Builder: ");
        Thread thread1 = new Thread(builder);
        Thread thread2 = new Thread(builder);

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
