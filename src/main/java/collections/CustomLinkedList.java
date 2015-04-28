package collections;

/**
 * Created by odudak on 28.04.15.
 * имплементируй методы и убери абстракт
 */
public class CustomLinkedList<T> {
    private T data;
    private CustomLinkedList<T> next;
    private CustomLinkedList<T> prev;

    public CustomLinkedList<T> getPrev() {
        return prev;
    }

    public void setPrev(CustomLinkedList<T> prev) {
        this.prev = prev;
    }

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

    void appendToEnd(T element) {
//        сделать
    }

    void pushOnTop(T element) {
//        сделать
    }

    @Override
    public String toString() {
        if (this.next != null && prev == null)
            return "[ " + data + ", " + this.next.toString();
        else if (this.next != null && prev != null) {
            return data + ", " + this.next.toString();
        } else
            return data + " ]";
    }
}
