package base.types;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TypeCastsTest {

    @Test
    public void testCastMe() throws Exception {
        Number i = new TypeCasts<Number>().castMe(new Integer(1));
        System.out.println(i);
        assertTrue(i instanceof Number);
        CharSequence o = new TypeCasts<CharSequence>().castMe("qwert");
        System.out.println(o);
        assertTrue(o instanceof CharSequence);
        Number b = new TypeCasts<Number>().castMe(new Byte("125"));
        System.out.println(b);
        assertTrue(b instanceof Number);
        Number s = new TypeCasts<Number>().castMe(new Short("10000"));
        System.out.println(s);
        assertTrue(s instanceof Number);
        Number l = new TypeCasts<Number>().castMe(new Long(200000));
        System.out.println(l);
        assertTrue(l instanceof Number);
        Comparable t = new TypeCasts<Comparable>().castMe(new Boolean("true"));
        System.out.println(t);
        assertTrue(t instanceof Comparable);
        Comparable c = new TypeCasts<Comparable>().castMe(new Character('\u0001'));
        System.out.println(c);
        assertTrue(c instanceof Comparable);
    }

}