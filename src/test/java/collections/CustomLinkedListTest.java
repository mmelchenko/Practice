package collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomLinkedListTest {
    String tst = "[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ]";

    @Test
    public void testToString() throws Exception {
        CustomLinkedList<Integer> cl = new CustomLinkedList<Integer>();
        cl.setData(11);
        for (int i = 10; i > 0; i--) {
            CustomLinkedList<Integer> bak = cl;
            cl = new CustomLinkedList<Integer>();
            cl.setData(i);
            cl.setNext(bak);
//            по идее это место надо будет изменить когда ты этот функционал всунешь в методы которые надо имплементировать
            bak.setPrev(cl);
        }
        assertEquals(tst, cl.toString());

    }

    @Test
    public void testAppendToEnd() throws Exception {
// сделать
    }

    @Test
    public void testPushOnTop() throws Exception {
// сделать
    }

}