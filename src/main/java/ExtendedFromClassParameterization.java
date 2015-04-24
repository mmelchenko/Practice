public class ExtendedFromClassParameterization<A, B, C> extends ClassParameterization {

    public final C c;

    public ExtendedFromClassParameterization(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public String toString() {
        return "(" + a + ", " + b + ", " + c + ")";
    }
}
