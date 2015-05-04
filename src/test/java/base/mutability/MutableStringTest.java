package base.mutability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by dtv on 04.05.2015.
 */
public class MutableStringTest {

    @Test
    public void mutableStringTest() {
        MutableString a = new MutableString("a");
        MutableString b = new MutableString("b");
        MutableString c = new MutableString("c");
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(b, c);
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(b.hashCode(), c.hashCode());
        a.setS("b");
        MutableString d = new MutableString("c");
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
