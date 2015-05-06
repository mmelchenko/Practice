package base.strings;

/**
 * Created by odudak on 28.04.15.
 * убедиться что конкатенация жрёт память
 */
public class Concat {

    private String string;
    private long freeMemory = Runtime.getRuntime().freeMemory();

    public Concat(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void memoryExpense(int iterations) {

        long endFreeMemory = 0;

        for (int i = 0; i < iterations; i++) {
            string += i;
            System.out.println(Runtime.getRuntime().freeMemory());
            if (i + 1 == iterations) {
                endFreeMemory = Runtime.getRuntime().freeMemory();
                System.out.println(endFreeMemory);
            }
        }

        System.out.println("Concatenation of " + iterations + " strings takes memory: " + (freeMemory - endFreeMemory));
    }
}
