package base.strings;

/**
 * Created by odudak on 28.04.15.
 * поработать с стринг буфером и указать оличия от билдера
 */
// Ответ: StringBuffer синхронизирован, а StringBuilder - нет.

public class Buffer implements Runnable {
    private int iterations;

    private StringBuffer stringBuffer = new StringBuffer();

    public Buffer(String s) {
        this.stringBuffer = new StringBuffer(s);
    }

    public Buffer(String string, int iterations) {

        this.stringBuffer = new StringBuffer(string);
        this.iterations = iterations;
    }

    public void setStringBuffer(StringBuffer stringBuffer) { this.stringBuffer = stringBuffer; }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public int getIterations() { return iterations; }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            stringBuffer.append("i = ");
            stringBuffer.append(i);
            stringBuffer.append(";" + System.getProperty("line.separator").toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
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
