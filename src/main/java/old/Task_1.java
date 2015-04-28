package old;

/**
 * The basic syntax of the Java programming language
 */
public class Task_1 {

    private int x;
    private int y;

    public Task_1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int sum() {
        return x + y;
    }

    public int multiple() {
        return x * y;
    }

    public int max() {
        return (x > y) ? x : y;
    }

    public int[] countToTen() {
        int[] result = new int[10];
        int i = 1;
        while (i < 11) {
            result[i-1] = i;
            i++;
        }
        return result;
    }
}
