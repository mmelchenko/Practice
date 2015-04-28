package old;

public class ClassParameterization<A, B> {

    public final A a;
    public final B b;

    public ClassParameterization(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return "(" + a + ", " + b + ")";
    }

    public static void main(String[] args) {
        ClassParameterization<String, Integer> classParameterization = new ClassParameterization<String, Integer>("old.ClassParameterization", new Integer(5));
        ExtendedFromClassParameterization<String, Integer, Boolean> extendedFromClassParameterization =
                new ExtendedFromClassParameterization<String, Integer, Boolean>("old.ExtendedFromClassParameterization", new Integer(9), new Boolean(true));

        System.out.println(classParameterization);
        System.out.println(extendedFromClassParameterization);
    }
}