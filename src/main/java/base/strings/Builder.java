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

    private StringBuilder stringBuilder = new StringBuilder();

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
    public void run() {
        for (int i = 0; i < 20; i++) {
            stringBuilder.append("If you can read this, ").append("that mean, that ").append(" StringBuilder is threadsafe.");
            randomWait();
            System.out.println(stringBuilder);
            this.setStringBuilder(new StringBuilder(""));
        }
    }

    public void randomWait() {
        try {
            Thread.currentThread().sleep((long)(3000*Math.random()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
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
