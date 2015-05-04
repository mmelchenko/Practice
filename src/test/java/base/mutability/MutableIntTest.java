package base.mutability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by dtv on 04.05.2015.
 */
public class MutableIntTest {

    @Test
    public void mutableIntTest() {
        MutableInt a = new MutableInt(5);
        MutableInt b = new MutableInt(10);
        MutableInt c = new MutableInt(15);
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(b, c);
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(b.hashCode(), c.hashCode());
        a.setI(10);
        MutableInt d = new MutableInt(15);
        assertEquals(a, b);
        assertNotEquals(b, d);
        assertNotEquals(a, d);
        assertEquals(c, d);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(b.hashCode(), d.hashCode());
        assertEquals(c.hashCode(), d.hashCode());
    }
}
