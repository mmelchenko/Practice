package base.types;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeCastsTest {

    @Test
    public void testCastMe() throws Exception {
        TypeCasts<Number> ntc = new TypeCasts<Number>();
        Object i = ntc.castMe(new Integer(1));
        assertTrue(i instanceof Number);
        Object o = ntc.castMe("qwert");
        assertFalse(o instanceof Number);
    }

}