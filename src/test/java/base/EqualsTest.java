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
//      Ответ:  метод equals() реализован мной таким образом, чтобы объекты с одинаковыми данными (состоянием)
//              воспринимались равными. Т.е. в данном примере a.equals(b) == true.
//              По умолчанию метод equals() сравнивает ссылки объектов, а не их содержание.
        assertEquals(a, c);
        assertEquals(b, c);
        assertEquals(a, b);
        assertEquals(b, a);
        EqualsOk d = new EqualsOk(1, "b");
        assertNotEquals(a, d);

//        то о чем я в классе писал
        EqualsOk2 child = new EqualsOk2(1, "a", 3);
//        мой эквалс видит разницу
        assertNotEquals(child, a);
//        а твой нет, так как все чайлды это инстансы парентов и транзитивность эквалса нарушена
//        перепиши с учетом наследования
        assertNotEquals(a, child);
    }

    static class EqualsOk2 extends EqualsOk {
        private int j;

        public EqualsOk2(int i, String s, int j) {
            super(i, s);
            this.j = j;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o == this) return true;
            boolean eq = true;
            if (EqualsOk2.class.equals(o.getClass())) {
                if (getI() != ((EqualsOk2) o).getI()) {
                    eq = false;
                }
                if (!getS().equals(((EqualsOk2) o).getS())) {
                    eq = false;
                }
                if (getJ() != ((EqualsOk2) o).getJ()) {
                    eq = false;
                }
            }
//            разные классы это уже не эквалс так ведь?
            else return false;
            return eq;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + j;
            return result;
        }
    }

    @Test
    public void testHashCode() throws Exception {
        EqualsOk a = new EqualsOk(1, "a");
        EqualsOk b = new EqualsOk(1, "a");
        EqualsOk c = new EqualsOk(1, "a");
        assertEquals(a.hashCode(), c.hashCode());
        assertEquals(b.hashCode(), c.hashCode());
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(b.hashCode(), a.hashCode());
        EqualsOk d = new EqualsOk(1, "b");
        assertNotEquals(a.hashCode(), d.hashCode());
    }
}