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

    static class SBThread extends Thread {
        private StringBuilder sb;
        private int prefix;
        private int count;

        SBThread(StringBuilder sb, int prefix, int count) {
            this.sb = sb;
            this.prefix = prefix;
            this.count = count;
        }

        @Override
        public void run() {
            for (int j = 0; j < count; j++) {
                sb.append(prefix);
            }

        }
    }

    @Test
    public void testMultithreadingBuilder() throws InterruptedException {
        int count = 10000;
        int acc = 0;
        int numRun = 100;
        for (int i = 0; i < numRun; i++) {
            acc += collect(count);
        }
        System.out.println(acc);
        assertNotEquals(acc / numRun, count * 3);
    }


    // must be equal n * 3 every time
    private int collect(int count) throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        SBThread t1 = new SBThread(sb, 1, count);
        SBThread t2 = new SBThread(sb, 2, count);
        SBThread t3 = new SBThread(sb, 3, count);
        t1.start();
        t3.start();
        t2.start();
        t1.join();
        t2.join();
        t3.join();
        return sb.length();
    }
}
