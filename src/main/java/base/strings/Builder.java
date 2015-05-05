package base.strings;

/**
 * Created by odudak on 28.04.15.
 * поработать с стринг билдером и указать оличия от буфера
 */
// Ответ: StringBuffer синхронизирован, а StringBuilder - нет.

public class Builder {

    StringBuilder stringBuilder = new StringBuilder();

    public Builder(String string) {
        this.stringBuilder = new StringBuilder(string);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Builder)) return false;

        Builder builder = (Builder) o;

        return !(stringBuilder != null ? !stringBuilder.equals(builder.stringBuilder) : builder.stringBuilder != null);

    }

    @Override
    public int hashCode() {
        return stringBuilder != null ? stringBuilder.hashCode() : 0;
    }
}
