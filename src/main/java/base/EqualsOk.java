package base;


/**
 * Created by odudak on 27.04.15.
 * <p>
 * реализовать оба метода и написать юнит тест для проверки
 * этот класс будет использоваться для проверки коллекций
 */
public class EqualsOk {
    private int i;
    private String s;

    public EqualsOk(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public String getS() {
        return s;
    }

    @Override
    public boolean equals(Object obj) {
//        забыл в начале нулл (вообще не обязон но сильно поднимает перформанс иноди)
        if (obj == null) return false;
        if (this == obj) return true;
//        тут не очень хорошо, я покажу в тестах почему (кроме этого инстансоф очень дорогая операция так как она идёт по всей иерархии классов
//        которая может быть оооочень длинной )))
        if (!(obj instanceof EqualsOk)) return false;

        EqualsOk equalsOk = (EqualsOk) obj;

        if (i != equalsOk.i) return false;
        return s.equals(equalsOk.s);

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + s.hashCode();
        return result;
    }
}
