package base;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by odudak on 27.04.15.
 *
 * реализовать оба метода и написать юнит тест для проверки
 */
public class Equals {
    private int i;
    private String s;

    public Equals(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
//        TODO
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
//        TODO
        throw new NotImplementedException();
    }
}
