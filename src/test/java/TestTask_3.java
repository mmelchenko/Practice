import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.assertTrue;

public class TestTask_3 {

    Task_3 task_3 = new Task_3();

    LinkedList<Task_3.Student> list1 = new LinkedList<Task_3.Student>();
    LinkedList<Task_3.Student> list2 = new LinkedList<Task_3.Student>();

    Task_3.Student student1 = task_3.createStudent("Ivan", 2);
    Task_3.Student student2 = task_3.createStudent("Anton", 3);
    Task_3.Student student3 = task_3.createStudent("Alex", 1);
    Task_3.Student student4 = task_3.createStudent("Jin", 4);
    Task_3.Student student5 = task_3.createStudent("Craig", 2);

    @Before
    public void init() {

        list1.add(student1);
        list1.add(student2);
        list1.add(student3);

        list2.add(student4);
        list2.add(student5);
    }

    @Test
    public void testPrintStudents() {
        LinkedList<Task_3.Student> expected = new LinkedList<Task_3.Student>();
        expected.add(student2);
        LinkedList<Task_3.Student> actual = task_3.printStudents(list1, 3);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testIntersect() {
        LinkedList<Task_3.Student> expected = new LinkedList<Task_3.Student>();
        expected.addAll(list1);
        expected.addAll(list2);
        LinkedList<Task_3.Student> actual = task_3.union(list1, list2);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testUnion() {
        LinkedList<Task_3.Student> expected = new LinkedList<Task_3.Student>();
        expected.add(student1);
        LinkedList<Task_3.Student> actual = task_3.intersect(list1, list2);
        assertTrue(actual.containsAll(expected));
    }
}
