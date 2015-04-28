package old.newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Получение текста из буфера ByteBuffers.
 */
public class ConversionOfData {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {

        FileChannel fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());//Не сработает!

        //Декодирование с использованием кодировки по умолчанию
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Декодировано в " + encoding + ": " + Charset.forName(encoding).decode(buffer));

        //Кодирование в печатной форме
        fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fileChannel.close();

        // Повторная попытка чтения
        fileChannel = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        //Использование CharBuffer для записи
        fileChannel = new FileOutputStream("data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24);  //Больше, чем необходимо
        buffer.asCharBuffer().put("Some text");
        fileChannel.write(buffer);
        fileChannel.close();

        //Чтение и вывод
        fileChannel = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
