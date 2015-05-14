package base.strings;

/**
 * Created by odudak on 28.04.15.
 * поработать с стринг билдером и указать оличия от буфера
 */
// Ответ: StringBuffer синхронизирован, а StringBuilder - нет.
/*
    ответ ок, но тут надо было наваять ченить типа класса который использует стринг билдер как основу для хранения данных
    и его уже в тестах гонять, на тех количествах что ты гонял его тут, жвм успевает поработать с ним и завершиться ещё до запуска других потоков
    слипать никчему, так как слип уменьшает вероятность перекрытия потоков
    вобщем переделай класс под что-то более кошерное и в тестах его уже помучай
*/
public class Builder implements Runnable {
    private int iterations;

    private StringBuilder stringBuilder = new StringBuilder();

    public Builder(String string) { this.stringBuilder = new StringBuilder(string); }

    public Builder(String string, int iterations) {

        this.stringBuilder = new StringBuilder(string);
        this.iterations = iterations;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public int getIterations() { return iterations; }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("i = ");
            stringBuilder.append(i);
            stringBuilder.append(";" + System.getProperty("line.separator").toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
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
