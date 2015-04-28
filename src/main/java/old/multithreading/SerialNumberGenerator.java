package old.multithreading;


public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber() {
        return serialNumber++; // Операция не являеться потокобезопасной
    }
}
