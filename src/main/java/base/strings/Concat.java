package base.strings;

/**
 * Created by odudak on 28.04.15.
 * убедиться что конкатенация жрёт память
 */
public class Concat {

    private String string;
    private StringBuilder stringBuilder;

    public Concat() {
        this.stringBuilder = new StringBuilder();
    }

    public Concat concatByPlus(String s) {
        this.string += s;
        return this;
    }

    public Concat concatByStringBuilder(String s) {
        this.stringBuilder.append(s);
        return this;
    }


}
