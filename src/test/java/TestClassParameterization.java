import org.junit.Test;
import static org.junit.Assert.*;

public class TestClassParameterization {

    @Test
    public void testClassParameterization() {
        ClassParameterization<String, Integer> classParameterization = new ClassParameterization<String, Integer>("ClassParameterization", new Integer(5));
        ExtendedFromClassParameterization<String, Integer, Boolean> extendedFromClassParameterization =
                new ExtendedFromClassParameterization<String, Integer, Boolean>("ExtendedFromClassParameterization", new Integer(9), new Boolean(true));
        String actual_1 = classParameterization.toString();
        String actual_2 = extendedFromClassParameterization.toString();
        String expected_1 = "(ClassParameterization, 5)";
        String expected_2 = "(ExtendedFromClassParameterization, 9, true)";
        assertTrue(expected_1.equals(actual_1) && expected_2.equals(actual_2));
    }
}
