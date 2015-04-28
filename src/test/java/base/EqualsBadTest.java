package base;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class EqualsBadTest {

    @Test
    public void testEquals() throws Exception {
        EqualsBad e1 = new EqualsBad(1, "s");
        EqualsBad e2 = new EqualsBad(1, "s");
        assertNotEquals(e1, e2);
    }

    @Test
    public void testHashCode() throws Exception {

    }
}