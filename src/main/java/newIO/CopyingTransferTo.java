package newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Использование метода transferTo() для соединения каналов.
 */
public class CopyingTransferTo {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("параметры Источник-Приемник");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel(),
                    out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        //Или
        //out.transferFrom(in, 0, in.size());
    }
}