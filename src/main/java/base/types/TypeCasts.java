package base.types;

/**
 * Created by odudak on 28.04.15.
 * провести касты всех примитивных типов одного в другой и посмотреть что отрезается
 */
public class TypeCasts<T> {
    public <E> T castMe(E t) {
        return (T) t;
    }
}
