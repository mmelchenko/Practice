package io.async.bytestream;

import exeptions.NotifyIsNull;
import interfaces.NotifyMe;

import java.io.File;

/**
 * Created by odudak on 28.04.15.
 */
public class AsyncByteOperations {


    public void writeByteArrayNotify(byte[] ba, File f, NotifyMe notify) throws NotifyIsNull {
        if (notify == null) throw new NotifyIsNull();
//        записать и уведомить

        notify.notifyMe();
    }

}
