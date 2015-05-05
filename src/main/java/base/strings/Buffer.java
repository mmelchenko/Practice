package base.strings;

/**
 * Created by odudak on 28.04.15.
 * поработать с стринг буфером и указать оличия от билдера
 */
// Ответ: StringBuffer синхронизирован, а StringBuilder - нет.

public class Buffer {

    private StringBuffer stringBuffer = new StringBuffer();

    public Buffer(String s) {
        this.stringBuffer = new StringBuffer(s);
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buffer)) return false;

        Buffer buffer = (Buffer) o;

        return !(stringBuffer != null ? !stringBuffer.equals(buffer.stringBuffer) : buffer.stringBuffer != null);

    }

    @Override
    public int hashCode() {
        return stringBuffer != null ? stringBuffer.hashCode() : 0;
    }
}
