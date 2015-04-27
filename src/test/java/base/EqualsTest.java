package base;

import org.junit.Test;

import static org.junit.Assert.*;

public class EqualsTest {

    @Test
    public void testEquals() throws Exception {
        Equals a = new Equals(1, "a");
        Equals b = new Equals(1, "a");
        Equals c = new Equals(1, "a");
//        тут ты мне должен написать камент под каждым вызовом что проверялось и для чего
        assertEquals(a,c);
        assertEquals(b,c);
        assertEquals(a,b);
        assertEquals(b,a);
        Equals d = new Equals(1, "b");
        assertNotEquals(a,d);
    }

    @Test
    public void testHashCode() throws Exception {

    }
}