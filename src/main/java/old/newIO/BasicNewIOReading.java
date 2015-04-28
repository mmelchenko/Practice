package old.newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Новый ввод/вывод.
 */
public class BasicNewIOReading {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        //Запись в файл
        FileChannel fileChannel = new FileOutputStream("data.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text ".getBytes()));
        fileChannel.close();
        //Добавление в конец файла
        fileChannel = new RandomAccessFile("data.txt", "rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap("Some text ".getBytes()));
        fileChannel.close();
        //Чтение из файла
        fileChannel = new FileInputStream("data.txt").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()) {
            System.out.println((char)byteBuffer.get());
        }
    }
}
