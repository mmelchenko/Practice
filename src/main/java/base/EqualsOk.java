package base;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by odudak on 27.04.15.
 *
 * реализовать оба метода и написать юнит тест для проверки
 * этот класс будет использоваться для проверки коллекций
 */
public class EqualsOk {
    private int i;
    private String s;

    public EqualsOk(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EqualsOk)) return false;

        EqualsOk equalsOk = (EqualsOk) obj;

        if (i != equalsOk.i) return false;
        return s.equals(equalsOk.s);

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + s.hashCode();
        return result;
    }
}
