package base.types;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeCastsTest {

    @Test
    public void testCastMe() throws Exception {
        TypeCasts<Number> ntc = new TypeCasts<Number>();
        Object i = ntc.castMe(new Integer(1));
        System.out.println(i);
        assertTrue(i instanceof Number);
        Object o = ntc.castMe("qwert");
        System.out.println(o);
        assertFalse(o instanceof Number);
        Object b = ntc.castMe(new Byte("125"));
        System.out.println(b);
        assertTrue(b instanceof Byte);
        Object s = ntc.castMe(new Short("10000"));
        System.out.println(s);
        assertTrue(s instanceof Short);
        Object l = ntc.castMe(new Long(200000));
        System.out.println(l);
        assertTrue(l instanceof Long);
        Object t = ntc.castMe(new Boolean("true"));
        System.out.println(t);
        assertTrue(t instanceof Boolean);
        Object c = ntc.castMe(new Character('\u0001'));
        System.out.println(c);
    }

}