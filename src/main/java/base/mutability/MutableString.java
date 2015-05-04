package base.mutability;

/**
 * Created by odudak on 28.04.15.
 * create mutable class with property accessor
 */
public class MutableString {
    
    private String s;

    public MutableString(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutableString)) return false;

        MutableString that = (MutableString) o;

        return !(s != null ? !s.equals(that.s) : that.s != null);

    }

    @Override
    public int hashCode() {
        return s != null ? s.hashCode() : 0;
    }
}
