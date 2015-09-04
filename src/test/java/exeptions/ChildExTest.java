package exeptions;

import org.junit.Test;

/**
 * write description about type cast over each method
 */

public class ChildExTest {

    @Test(expected = ParentEx.class)
    public void ex1() throws ParentEx {
        new AE().a();                   // throw ParentEx and getting ParentEx
    }

    @Test(expected = ParentEx.class)
    public void ex11() throws ParentEx {
        new AD().a();                   // throw ChildEx, but that's OK.
    }                                   // Because ChildEx extends ParentEx.

    @Test(expected = ChildEx.class)
    public void ex2() throws ChildEx {
        new AD().a();                   // throw ChildEx and getting ChildEx
    }

    @Test(expected = ChildEx.class)
    public void ex22() throws ParentEx {
        new AD().a();                   // throw ChildEx, but that's OK.
    }                                   // Because ChildEx extends ParentEx.

    @Test(expected = Error.class)
    public void ex3() throws ParentEx {
        new AC().a();                   // throw Error and getting Error
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