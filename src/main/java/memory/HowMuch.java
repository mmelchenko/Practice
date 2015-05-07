package memory;

/**
 * Created by odudak on 07.05.15.
 */
public class HowMuch {
    public long howManyFree() {
        return Runtime.getRuntime().freeMemory();
    }

    public long howManyTotal() {
        return Runtime.getRuntime().totalMemory();
    }

}
