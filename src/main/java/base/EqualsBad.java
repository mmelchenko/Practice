package base;


import java.util.Random;

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
//        та же хрень
//        return super.equals(obj);
        return false;
    }

    @Override
    public int hashCode() {
//        фигово
//        return super.hashCode();
        return new Random().nextInt();
    }
}
