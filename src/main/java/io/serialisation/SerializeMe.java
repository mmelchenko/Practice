package io.serialisation;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by odudak on 08.05.15.
 */
public class SerializeMe implements Serializable {

//    что надо сделать чтобы сериализовать обьект ?
//    что будеть в os после десериализации ?


    private OutputStream os;
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
