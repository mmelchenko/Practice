package base.strings;

/**
 * Created by odudak on 28.04.15.
 * поработать с стринг буфером и указать оличия от билдера
 */
// Ответ: StringBuffer синхронизирован, а StringBuilder - нет.

public class Buffer implements Runnable {

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
    public void run() {
        for (int i = 0; i < 20; i++) {
            stringBuffer.append("If you can read this,").append("that mean,that ").append("StringBuffer is threadsafe. ");
            randomWait();
            System.out.println(stringBuffer);
            this.setStringBuffer(new StringBuffer(""));
        }
    }

    public void randomWait() {
        try {
            Thread.currentThread().sleep((long) (3000 * Math.random()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
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
