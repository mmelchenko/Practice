package IOpractice;

import java.io.PrintWriter;

/**
 * Замена System.in на PrintWriter.
 */
public class SystemInReplacement {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello from PrintWriter.");
    }
}
