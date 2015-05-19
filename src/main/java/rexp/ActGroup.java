package rexp;

/**
 * Created by odudak on 19.05.15.
 */
public class ActGroup implements Act<String> {
    private String rexp;

    public ActGroup(String rexp) {
        this.rexp = rexp;
    }

    @Override
    public String act() {
//        do something and return group 1 selection
        return null;
    }
}
