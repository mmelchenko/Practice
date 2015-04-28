package old;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestMyException {

    MyException myException = new MyException();

    @Test
    public void testEmpty() throws MyException {
        try{
            myException.empty();
            fail("old.MyException wasn't thrown from method empty()");
        } catch (MyException e) {
            e.printStackTrace(System.out);
            assertNotNull(e);
        }
    }

    @Test
    public void testWithMessage() throws MyException {
        try{
            myException.withMessage();
            fail("old.MyException wasn't thrown from method withMessage()");
        } catch (MyException e) {
            e.printStackTrace(System.out);
            assertNotNull(e);
        }
    }

    @Test
    public void testWithMessageAndSomeValue() throws MyException {
        try{
            myException.withMessageAndSomeValue();
            fail("old.MyException wasn't thrown from method withMessageAndSomeValue()");
        } catch (MyException e) {
            e.printStackTrace(System.out);
            assertNotNull(e);
        }
    }
}
