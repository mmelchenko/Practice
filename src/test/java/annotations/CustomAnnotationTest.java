package annotations;

import org.junit.Test;

public class CustomAnnotationTest {

    @Test
    public void testRuntime() throws Exception {
//        проверить доступность аннотации в рантайме
    }

    @Test
    public void testSrc() throws Exception {
//        проверить недоступность нигде(аннотация маркер)
    }

    @Test
    public void testClazz() throws Exception {
//        проверить недоступность в рантайме и доступность в CustomAnnotation.class
    }
}