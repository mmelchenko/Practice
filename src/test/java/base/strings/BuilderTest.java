package base.strings;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by dtv on 05.05.2015.
 */
public class BuilderTest {

    @Test
    public void builderTest() {
        Builder builder = new Builder("Helpful");
        assertEquals(builder.getStringBuilder().indexOf("l"), 2);
        assertEquals(builder.getStringBuilder().indexOf("l", 3), 6);
        assertEquals(builder.getStringBuilder().lastIndexOf("l"), 6);
        builder.getStringBuilder().replace(0, 4, "Mind");
        assertEquals(builder.getStringBuilder().toString(), "Mindful");
        assertEquals(builder.getStringBuilder().charAt(4), 'f');
    }
}
