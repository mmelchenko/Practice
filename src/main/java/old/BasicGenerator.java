package old;

public class BasicGenerator<T> implements Generator {
    private Class<T> type;
    public BasicGenerator(Class<T> type) {
        this.type = type;
    }
    public T next() {
        try {
            // Проверка доступности класса
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}
