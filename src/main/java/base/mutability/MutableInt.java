package base.mutability;

/**
 * Created by odudak on 28.04.15.
 * create mutable class with property accessor
 */
public class MutableInt {
    
    private int i;

    public MutableInt(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
//        тут тоже починить эквалс
        if (this == o) return true;
        if (!(o instanceof MutableInt)) return false;

        MutableInt that = (MutableInt) o;

        return i == that.i;

    }

    @Override
    public int hashCode() {
        return i;
    }
}
