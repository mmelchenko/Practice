package base.strings;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by dtv on 06.05.2015.
 */
public class ConcatTest {

    @Test
    public void concatTest() {
        Concat concat = new Concat("");
        concat.memoryExpense(250);               // ?????? ??????????? ?????????? ????????, ??? ??????? String.intern()
        assertNotEquals(concat.getString(), ""); // ?? ????????????. ? ????? ?????????? ?????? ??????. ???????? JVM ?? ??????????(
    }
}
