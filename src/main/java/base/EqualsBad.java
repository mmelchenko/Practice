package base;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by odudak on 28.04.15.
 */
public class EqualsBad {
    private int i;
    private String s;

    public EqualsBad(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
//        TODO реализовать метод
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
//        TODO реализовать метод
        throw new NotImplementedException();
    }
}
