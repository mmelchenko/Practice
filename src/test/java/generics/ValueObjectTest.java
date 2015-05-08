package generics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueObjectTest {
    @Test
    public void testGeneric() {
//        почитать в нете нахера нужны эти велью обжекты
        ValueObject<Integer> vi = new ValueObject<Integer>(10);
        assertEquals(vi.val, Integer.valueOf(10));
        ValueObject<String> vs = new ValueObject<String>("str");
        assertEquals(vs.val, "str");
    }

}