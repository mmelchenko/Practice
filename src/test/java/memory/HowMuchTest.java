package memory;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class HowMuchTest {

    @Test
    public void testHowManyFree() throws Exception {
        HowMuch hm = new HowMuch();
        long f = hm.howManyFree();
        System.out.println(f);
        assertNotNull(f);
    }

    @Test
    public void testHowManyTotal() throws Exception {
        HowMuch hm = new HowMuch();
        long f = hm.howManyTotal();
        System.out.println(f);
        assertNotNull(f);
    }
}