package exeptions;

import org.junit.Test;

/**
 * write description about type cast over each method
 */

public class ChildExTest {

    @Test(expected = ParentEx.class)
    public void ex1() throws ParentEx {
        new AE().a();
    }

    @Test(expected = ParentEx.class)
    public void ex11() throws ParentEx {
        new AD().a();
    }

    @Test(expected = ChildEx.class)
    public void ex2() throws ChildEx {
        new AD().a();
    }

    @Test(expected = ChildEx.class)
    public void ex22() throws ParentEx {
        new AD().a();
    }

    @Test(expected = Error.class)
    public void ex3() throws ParentEx {
        new AC().a();
    }

    static class AE {

        public void a() throws ParentEx {
            throw new ParentEx();
        }
    }

    static class AD extends AE {

        @Override
        public void a() throws ChildEx {
            throw new ChildEx();
        }
    }

    static class AC extends AD {

        @Override
        public void a() {
            throw new Error();
        }
    }

}