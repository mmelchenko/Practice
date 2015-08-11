package collections;

/**
 * Created by odudak on 28.04.15.
 * проверить как влияет на хешированные коллекции неправильная реализация хеш кода и эквалс
 */
public class WrongEqualsHashCode {

    CustomLinkedList<Integer> first = new CustomLinkedList<Integer>();
    CustomLinkedList<Integer> second = new CustomLinkedList<Integer>();

    public void init() {
        for (int i = 1; i < 12; i++) {
            first.appendToEnd(i);
            second.appendToEnd(i);
        }
    }

    public CustomLinkedList<Integer> getFirst() {
        return first;
    }

    public CustomLinkedList<Integer> getSecond() {
        return second;
    }

    public void putAndCantGet() {

    }

    public static void main(String[] args) {
        WrongEqualsHashCode test = new WrongEqualsHashCode();
        test.init();
        CustomLinkedList<Integer> f = test.getFirst();
        CustomLinkedList<Integer> s = test.getSecond();

        System.out.println("Hash code verification: " + (f.hashCode() == s.hashCode()));
        System.out.println("Equals verification: " + f.equals(s));
    }
}
