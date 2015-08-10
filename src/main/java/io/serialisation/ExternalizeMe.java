package io.serialisation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by odudak on 08.05.15.
 */
public class ExternalizeMe implements Externalizable {

    private List<String> ls;

    public ExternalizeMe(List<String> ls) {
        this.ls = ls;
    }

    public List<String> getLs() {
        return ls;
    }

    public void setLs(List<String> ls) {
        this.ls = ls;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalizeMe that = (ExternalizeMe) o;
//      херовенький вариант, но пусть (обьяснить мне почему)
        if (ls != null ? !ls.equals(that.ls) : that.ls != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return ls != null ? ls.hashCode() : 0;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//      сделать свою сейвилку в файл
        out.writeObject(this.getLs());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//      сделать свою читалку в обьект
        this.setLs((List<String>) in.readObject());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < ls.size(); i++) {
            if (i + 1 == ls.size()) {
                result.append(ls.get(i) + "]");
                return String.valueOf(result);
            }
            result.append(ls.get(i) + ", ");
        }
        return String.valueOf(result);
    }
}
