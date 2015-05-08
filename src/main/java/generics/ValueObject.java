package generics;

/**
 * Created by odudak on 08.05.15.
 */
public class ValueObject<T> {
    public final T val;

    public ValueObject(T val) {
        this.val = val;
    }
}
