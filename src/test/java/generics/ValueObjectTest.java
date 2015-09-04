package generics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueObjectTest {
    @Test
    public void testGeneric() {
//        почитать в нете нахера нужны эти велью обжекты
//        прочитал... вобщем написано, что это маленькие неизменные объекты
//        которые ориентированы на внутренее состояние. Также рекомендуеться переопределить
//        иквалс и хэшкод (как и для многих других объектов), и еще клонирование.
//        Правда я не очень понял разницу между ним и DTO(data transfer object).
        ValueObject<Integer> vi = new ValueObject<Integer>(10);
        assertEquals(vi.val, Integer.valueOf(10));
        ValueObject<String> vs = new ValueObject<String>("str");
        assertEquals(vs.val, "str");
    }

}