import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class TestTask_2 {

    Task_2 task_2 = new Task_2();

    @Test
    public void testUsdConverter() {
        double usd = task_2.usdConverter(1000, 20.7);
        assertEquals(20700,usd,0);
    }

    @Test
    public void testIncreaseMassive() {
        double[] basic = {10.0,20.0,30.0,40,50.0};
        double[] expected = {11.0,22.0,33.0,44.0,55.0};
        double[] mas = task_2.increaseMassive(basic,10);
        assertTrue(Arrays.equals(expected, mas));
    }

    @Test
    public void testTextCensor() {
        String basic = "Just copy and paste material id and “String for parse” and see how the script is finished.";
        String expected = "Just copy and paste material id and “String for *censored*” and see how the script is finished.";
        String actual = task_2.stringCensor(basic, "parse");
        assertTrue(expected.equals(actual));
    }
}
