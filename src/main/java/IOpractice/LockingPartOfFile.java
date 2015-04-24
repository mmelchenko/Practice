package IOpractice;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Блокирование части отображаемого файла.
 */
public class LockingPartOfFile {
    static final int LENGTH = 0x8FFFFFF; //128 Mb
    static FileChannel fileChannel;
    public static void main(String[] args) throws Exception {
        fileChannel = new RandomAccessFile("test.txt", "rw").getChannel();
        MappedByteBuffer out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for(int i = 0; i < LENGTH; i++) {
            out.put((byte)'x');
        }
        new LockAndModify(out, 0, 0 + LENGTH/3);
        new LockAndModify(out, LENGTH/2, LENGTH/2 + LENGTH/4);
    }
    private static class LockAndModify extends Thread {
        private ByteBuffer buffer;
        private int start, end;
        LockAndModify(ByteBuffer byteBuffer, int start, int end) {
            this.start = start;
            this.end = end;
            byteBuffer.limit(end);
            byteBuffer.position(start);
            buffer = byteBuffer.slice();
            start();
        }
        public void run() {
            try {
                //Монопольная блокировка без перекрытия
                FileLock fileLock = fileChannel.lock(start, end, false);
                System.out.println("Заблокировано от " + start + " до " + end);
                //Модификация
                while(buffer.position() < buffer.limit() - 1) {
                    buffer.put((byte)(buffer.get() + 1));
                }
                fileLock.release();
                System.out.println("Освобождено от " + start + " до " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
