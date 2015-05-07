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

    @Before
    public void init() {
        c1 = new Concat();
        c2 = new Concat();
        max = 20000;
        Runtime.getRuntime().gc();
    }

    private long getMemoryDelta() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }


    private long testConcatByPlus() {
        long counter = 0;
        for (int i = 0; i < max; i++) {
            counter += getMemoryDelta();
            counter /= 2;
            c1 = c1.concatByPlus(StringConstants.CONCAT_STRING);
        }
        return counter;
    }

    private long testConcatByStringBuilder() {
        long counter = 0;
        for (int i = 0; i < max; i++) {
            counter += getMemoryDelta();
            counter /= 2;
            c1 = c1.concatByStringBuilder(StringConstants.CONCAT_STRING);
        }
        return counter;
    }

    @Test
    public void compare() {
        long plus = testConcatByPlus();
        System.out.println(plus);
        long builder = testConcatByStringBuilder();
        System.out.println(builder);
        assertTrue(plus < builder);
    }
}
