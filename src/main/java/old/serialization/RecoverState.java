package old.serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Восстановление состояния вымышленной системы CAD.
 */
public class RecoverState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("State.out"));
        // Данные считываются в том порядке, в котором они были записаны
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>)inputStream.readObject();
        Line.deserializeStaticState(inputStream);
        List<Shape> shapes = (List<Shape>)inputStream.readObject();
        System.out.println(shapes);
    }
}
