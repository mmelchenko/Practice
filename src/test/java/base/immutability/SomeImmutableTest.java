package base.immutability;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SomeImmutableTest {

    @Test
    public void testImmutabilityNameField() throws Exception {
        SomeImmutable someImmutable = new SomeImmutable("Some", 25);
        try {
            Field name = SomeImmutable.class.getDeclaredField("name");
            name.setAccessible(false);
            name.set(someImmutable, "Another name");
        } catch (IllegalAccessException e) {
            assertThat(e.getMessage(), is("Class base.immutability.SomeImmutableTest " +
                    "can not access a member of class " +
                    "base.immutability.SomeImmutable with modifiers \"private\""));
        }
    }

    @Test
    public void testImmutabilityAgeField() throws Exception {
        SomeImmutable someImmutable = new SomeImmutable("Some", 25);
        try {
            Field name = SomeImmutable.class.getDeclaredField("age");
            name.setAccessible(false);
            name.set(someImmutable, 30);
        } catch (IllegalAccessException e) {
            assertThat(e.getMessage(), is("Class base.immutability.SomeImmutableTest " +
                    "can not access a member of class " +
                    "base.immutability.SomeImmutable with modifiers \"private\""));
        }
    }
}
