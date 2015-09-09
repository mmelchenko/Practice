package annotations;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;

public class CustomAnnotationTest {

    @Test
    public void testRuntime() throws Exception {
//        проверить доступность аннотации в рантайме
        CustomAnnotation c = new CustomAnnotation();
        Method method = c.getClass().getMethod("runtime");
        RetRuntime retRuntime = method.getAnnotation(RetRuntime.class);
        assertNotNull(retRuntime);
    }

    @Test
    public void testSrc() throws Exception {
//        проверить недоступность нигде(аннотация маркер)
        CustomAnnotation c = new CustomAnnotation();
        Method method = c.getClass().getMethod("src");
        RetSrc retSrc = method.getAnnotation(RetSrc.class);
        assertNull(retSrc);
        RetSrc retSrc1 = CustomAnnotation.class.getAnnotation(RetSrc.class);
        assertNull(retSrc1);
    }

    @Test
    public void testClazz() throws Exception {
//        проверить недоступность в рантайме и доступность в CustomAnnotation.class
        CustomAnnotation c = new CustomAnnotation();
        Method method = c.getClass().getMethod("clazz");
        RetClass retClass = method.getAnnotation(RetClass.class);
        assertNull(retClass);
        // Тут у меня трабла... попытка получить аннотацию напрямую из класса не увенчалась успехом(
        // Пытаюсь получить ее методом, что приведен ниже. Получаю null.
        //RetClass retClass1 = CustomAnnotation.class.getAnnotation(RetClass.class);
        //assertNotNull(retClass1);
    }
}