package IOpractice;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Блокировка файлов.
 */
public class FileLocking {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        FileLock fileLock = fileOutputStream.getChannel().tryLock();
        if (fileLock != null) {
            System.out.println("Файл заблокирован");
            TimeUnit.MILLISECONDS.sleep(10000);
            fileLock.release();
            System.out.println("Блокировка снята");
        }
        fileOutputStream.close();
    }
}
