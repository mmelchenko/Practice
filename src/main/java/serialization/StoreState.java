package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Сохранение системы вымышленной системы CAD.
 */
abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random random = new Random(47);
    private static int counter = 0;
    public abstract void setColor(int newColor);
    public abstract int getColor();
    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }
    public String toString() {
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }
    public static Shape randomFactory() {
        int xVal = random.nextInt(100);
        int yVal = random.nextInt(100);
        int dim = random.nextInt(100);
        switch(counter++ % 3) {
            default:
            case 0: return new Circle(xVal, yVal, dim);
            case 1: return new Square(xVal, yVal, dim);
            case 3: return new Line(xVal, yVal, dim);
        }
    }
}

class Circle extends Shape {
    private static int color = RED;
    public Circle(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    public int getColor() {
        return color;
    }
}

class Square extends Shape {
    private static int color;
    public Square(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
        color = RED;
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static int color = RED;
    public static void serializeStaticState(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeInt(color);
    }
    public static void deserializeStaticState(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        color = inputStream.readInt();
    }
    public Line(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }
    public void setColor(int newColor) {
        color = newColor;
    }
    public int getColor() {
        return color;
    }
}

public class StoreState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();
        // Добавление ссылок на объекты класса:
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);
        List<Shape> shapes = new ArrayList<Shape>();
        // Создание фигур
        for(int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory());
        }
        // Назначение всех статических цветов:
        for(int i = 0; i < 10; i++) {
            ((Shape)shapes.get(i)).setColor(Shape.GREEN);
        }
        // Сохранение вектора состояния:
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("State.out"));
        outputStream.writeObject(shapeTypes);
        Line.serializeStaticState(outputStream);
        outputStream.writeObject(shapeTypes);
        // Вывод фигур
        System.out.println(shapes);
    }
}
