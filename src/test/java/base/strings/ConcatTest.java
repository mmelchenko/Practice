package base.strings;

import constants.StringConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by dtv on 06.05.2015.
 */
public class ConcatTest {
    Concat c1;
    Concat c2;
    int max;
    long mem;

    @Before
    public void init() {
        c1 = new Concat();
        c2 = new Concat();
        max = 20000;
        mem = Runtime.getRuntime().totalMemory();
    }

    private long getMemoryDelta(long start, long stop) {
        return stop - start;
    }


    private long testConcatByPlus() {
        long start = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < max; i++) {
            c1 = c1.concatByPlus(StringConstants.CONCAT_STRING);
        }
        long stop = Runtime.getRuntime().totalMemory();
        return getMemoryDelta(start, stop);
    }

    private long testConcatByStringBuilder() {
        long start = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < max; i++) {
            c1 = c1.concatByStringBuilder(StringConstants.CONCAT_STRING);
        }
        long stop = Runtime.getRuntime().totalMemory();
        return getMemoryDelta(start, stop);
    }

    @Test
    public void compare() {
        System.out.println("start: " + mem);
        System.out.println("free: " + Runtime.getRuntime().freeMemory());
        long builder = testConcatByStringBuilder();
        System.out.println("builder: " + builder);
        System.out.println("free: " + Runtime.getRuntime().freeMemory());
        long plus = testConcatByPlus();
        System.out.println("plus: " + plus);
        assertTrue(plus > builder);
    }
}
