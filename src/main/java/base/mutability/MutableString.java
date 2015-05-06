package base.mutability;

import constants.StringConstants;

/**
 * Created by odudak on 28.04.15.
 * create mutable class with property accessor
 */
public class MutableString {

    private StringBuilder s;

    public MutableString(String s) {
//        Мутабельность означает что раз созанный обьект уже не пересоздаётся заново
        this.s = new StringBuilder();
        this.s.append(s);
    }

    public String getS() {
        return s.toString();
    }

    public void setS(String s) {
//        он меняется так как нам надо
        this.s.delete(0, s.length());
        this.s.append(s);
    }

    public void add(String s) {
//       надо менять сам контейнер но он всегда должен быть тот же
        this.s.append(s);
    }

    //    TODO сделать удаляху и дописать юнит под неё
    public boolean deleteSubstring(String s) {
        throw new RuntimeException(StringConstants.NOT_IMPLEMENTED_YET);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutableString)) return false;

        MutableString that = (MutableString) o;

        return !(s != null ? !s.equals(that.s) : that.s != null);

    }

    @Override
    public int hashCode() {
        return s != null ? s.hashCode() : 0;
    }
}
