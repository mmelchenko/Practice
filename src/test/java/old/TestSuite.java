package old;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite old.Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestTask_1.class,
        TestTask_2.class,
        TestTask_3.class,
        TestMyException.class,
        TestTask_4.class,
        TestClassParameterization.class
})
public class TestSuite {
}
