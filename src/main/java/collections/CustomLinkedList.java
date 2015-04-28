package collections;

/**
 * Created by odudak on 28.04.15.
 * имплементируй методы и убери абстракт
 */
abstract public class CustomLinkedList<T> {
    private T data;
    private CustomLinkedList<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CustomLinkedList<T> getNext() {
        return next;
    }

    public void setNext(CustomLinkedList<T> next) {
        this.next = next;
    }

    abstract void appendToEnd(T element);

    abstract void pushOnTop(T element);
}
