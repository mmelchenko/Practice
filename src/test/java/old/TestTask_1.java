package old;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestTask_1 {

    Task_1 task_1 = new Task_1(3, 4);

    @Test
    public void testSum() {
        int sum = task_1.sum();
        assertTrue(sum == 7);
    }

    @Test
    public void testMultiple() {
        int mul = task_1.multiple();
        assertTrue(mul == 12);
    }

    @Test
    public void testMax() {
        int max = task_1.max();
        assertTrue(max == 4);
    }

    @Test
    public void testCountToTen() {
        int[] count = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] fromMethod = task_1.countToTen();
        assertTrue(Arrays.equals(count, fromMethod));
    }
}
