package rexp;

/**
 * Created by odudak on 19.05.15.
 */
public class RexpProcessor<T> {
    public T executeRexp(Act<T> actor) {
        return actor.act();
    }
}
