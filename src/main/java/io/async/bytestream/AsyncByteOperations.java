package io.async.bytestream;

import exeptions.NotifyIsNull;
import interfaces.NotifyMe;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.*;

/**
 * Created by odudak on 28.04.15.
 */
public class AsyncByteOperations {

    NotifyMe notify = new NotifyMe() {
        @Override
        public void notifyMe() {
            System.out.println("writeByteArrayNotify() method is done.");
        }
    };

    public NotifyMe getNotify() {
        return notify;
    }

    public void writeByteArrayNotify(byte[] ba, File f, NotifyMe notify) throws NotifyIsNull {
        if (notify == null) throw new NotifyIsNull();
//        записать и уведомить
        if (!f.exists()) {
            f = new File("async.txt");
            System.out.println("Provided file doesn't exist.");
            System.out.println("So default file 'async.txt' has been created.");
        }

        ByteBuffer buffer = ByteBuffer.wrap(ba);

        Path path = Paths.get(f.getAbsolutePath());
        AsynchronousFileChannel channel = null;
        try {
            channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("Exception during AsynchronousFileChannel initialization");
            e.printStackTrace();
        }

        CompletionHandler handler = new CompletionHandler() {
            @Override
            public void completed(Object result, Object attachment) {
                System.out.println(attachment + " completed and " + result + " bytes are written.");
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.out.println(attachment + " failed with exception:");
                e.printStackTrace();
            }
        };


        channel.write(buffer, 0, "Write async operation", handler);
        try {
            channel.close();
        } catch (IOException e) {
            System.out.println("Exception during AsynchronousFileChannel closure");
            e.printStackTrace();
        }

        notify.notifyMe();
    }
}
