package io.serialisation;

import java.io.*;

/**
 * Created by odudak on 08.05.15.
 */
public class SerializeMe implements Serializable {

//    что надо сделать чтобы сериализовать обьект ?
//    1. Объект должен реализовывать интерфейс java.io.Serializable (также объкт может унаследовать эту реализацию)
//    2. Сохраняемый объект должен пометить все свои несериализуемые поля как transient.
//    что будеть в os после десериализации ?
//    java.io.StreamCorruptedException: invalid stream header


    transient private OutputStream os;
    private String s;


    public SerializeMe(OutputStream os, String s) {
        this.os = os;
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializeMe that = (SerializeMe) o;

        if (s != null ? !s.equals(that.s) : that.s != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return s != null ? s.hashCode() : 0;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }
}
