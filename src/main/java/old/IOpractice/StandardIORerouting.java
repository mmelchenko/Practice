package old.IOpractice;

import java.io.*;

/**
 * Перенаправление стандартного ввода/вывода.
 */
public class StandardIORerouting {
    public static void main(String[] args) throws IOException{
        PrintStream console = System.out;
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("StandardIORerouting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(inputStream);
        System.setOut(out);
        System.setErr(out);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string;
        while((string = bufferedReader.readLine()) != null) {
            System.out.println(string);
        }
        out.close();
        System.setOut(console);
    }
}
