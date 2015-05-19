package rexp;

/**
 * Created by odudak on 19.05.15.
 */
public class ActMatch implements Act<Boolean> {
    private String rexp;

    public ActMatch(String rexp) {
        this.rexp = rexp;
    }

    @Override
    public Boolean act() {
//        check if rexp match
        return null;
    }
}
