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
        System.out.println("Current = " + current);

        if (current.size() == 0) {
            linkFirst(element);
            return;
        }

        while (current.getPrev() != null) {
            System.out.println("Prev -" + current.getPrev() + "-");
            current = current.getPrev();
            System.out.println("Current now = " + current);
        }

        current.setPrev(data);
        data.setNext(current);
        size++;
        System.out.println("data! = " + data);
        System.out.println("this.next now = " + this.next);
        System.out.println("this.prev now = " + this.prev);
    }

    @Override
    public String toString() {
        if (this.next == null && prev != null) {
            return this.prev.toString() + ", " + data + " ]";
        } else
        if (this.next != null && prev == null)
            return "[ " + data + ", " + this.next.toString();
        else if (this.next != null && this.prev != null) {
            return data + ", " + this.next.toString();
        } else {
            return data + " ]";
        }
    }

    private void linkFirst(T element) {
        this.setData(element);
        size++;
    }


    public static void main(String[] args) {
        CustomLinkedList<Integer> cl = new CustomLinkedList<Integer>();
        for (int i = 1; i < 12; i++) {
            cl.pushOnTop(i);
        }
        System.out.println("cltoend: " + cl);
    }
}
