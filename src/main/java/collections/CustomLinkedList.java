package collections;

/**
 * Created by odudak on 28.04.15.
 * имплементируй методы и убери абстракт
 */
public class CustomLinkedList<T> {
    private T data;
    private CustomLinkedList<T> next = null;
    private CustomLinkedList<T> prev = null;
    private int size = 0;

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

    public int size() { return size; }

    void appendToEnd(T element) {
        CustomLinkedList<T> data = new CustomLinkedList<T>();
        data.setData(element);
        CustomLinkedList<T> current = this;
        if (current.size() == 0) {
            linkFirst(element);
            return;
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(data);
        data.setPrev(current);
        size++;
    }

    void pushOnTop(T element) {
        CustomLinkedList<T> data = new CustomLinkedList<T>();
        data.setData(element);
        CustomLinkedList<T> current = this;

        if (current.size() == 0) {
            linkFirst(element);
            return;
        }

        while (current.getPrev() != null) {
            current = current.getPrev();
        }

        current.setPrev(data);
        data.setNext(current);
        size++;
    }

    @Override
    public String toString() {
        if (this.next == null && prev != null) {
            return data + " ]";
        }
        else if (this.next != null && this.prev != null) {
            return data + ", " + this.next.toString();
        }
        else if (this.next != null && prev == null)
            return "[ " + data + ", " + this.next.toString();
        else {
            return data + " ]";
        }
    }

    private void linkFirst(T element) {
        this.setData(element);
        size++;
    }

    public String toStringForPushOnTop() {
        if (this.next == null && prev != null) {
            return this.prev.toStringForPushOnTop() + data + " ]";
        }
        else if (this.next != null && this.prev != null) {
            return this.prev.toStringForPushOnTop() + data + ", ";
        }
        else if (this.next != null && prev == null)
            return  "[ " + data + ", ";
        else {
            return "[]";
        }
    }
}
