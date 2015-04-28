package old;

public class MyException extends Exception{

    private int someValue;

    public MyException() {
        System.out.println("Empty constructor used.");
    }
    public MyException(String message) {
        super(message);
        System.out.println("Constructor with message used.");
    }
    public MyException(String message, int someValue) {
        super(message);
        this.someValue = someValue;
        System.out.println("Constructor with message and value used.");
    }

    public String getMessage() {
        return "More detailed message: " + someValue + " " + super.getMessage();
    }

    public static void empty() throws MyException {
        System.out.println("old.MyException from empty()");
        throw new MyException();
    }

    public static void withMessage() throws MyException {
        System.out.println("old.MyException from withMessage()");
        throw new MyException("Throw from withMessage()...");
    }

    public static void withMessageAndSomeValue() throws MyException {
        System.out.println("old.MyException from withMessageAndSomeValue()");
        throw new MyException("Throw from withMessageAndSomeValue()...", 5);
    }

    public static void main(String[] args) {

    }
}
