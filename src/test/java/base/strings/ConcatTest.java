package base.strings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by dtv on 06.05.2015.
 */
public class ConcatTest {
    Concat c1;
    Concat c2;
    int divider;
    int max;

    @Before
    public void init() {
        c1 = new Concat();
        c2 = new Concat();
        divider = 10000;
        max = 100000;
    }

    private long getMemoryDelta() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }


    private long testConcatByPlus() {
        long counter = 0;
        for (int i = 0; i < max; i++) {
            counter += getMemoryDelta();
            c1 = c1.concatByPlus(String.valueOf(i));
        }
        return counter / max;
    }

    private long testConcatByStringBuilder() {
        long counter = 0;
        for (int i = 0; i < max; i++) {
            counter += getMemoryDelta();
            c1 = c1.concatByStringBuilder(String.valueOf(i));
        }
        return counter / max;
    }

    @Test
    public void compare() {
        long plus = testConcatByPlus();
        long builder = testConcatByStringBuilder();
        assertTrue(plus > builder);
    }
}
