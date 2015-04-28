package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EqualsTest {

    @Test
    public void testEquals() throws Exception {
        EqualsOk a = new EqualsOk(1, "a");
        EqualsOk b = new EqualsOk(1, "a");
        EqualsOk c = new EqualsOk(1, "a");
//        тут ты мне должен написать камент под каждым вызовом что проверялось и для чего
        assertEquals(a,c);
        assertEquals(b,c);
        assertEquals(a,b);
        assertEquals(b,a);
        EqualsOk d = new EqualsOk(1, "b");
        assertNotEquals(a,d);
    }

    @Test
    public void testHashCode() throws Exception {

    }
}