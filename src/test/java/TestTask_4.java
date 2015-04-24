import org.junit.Test;
import static org.junit.Assert.*;

public class TestTask_4 {

    Task_4 task_4 = new Task_4("1985-04-29 01:34");

    @Test
    public void testTimeFromBirthday() {
        String diff = task_4.timeFromBirthDay();
        System.out.println(diff);
        assertNotNull(diff);
    }
}
