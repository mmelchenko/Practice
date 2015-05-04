package base.immutability;

/**
 * Created by odudak on 28.04.15.
 * create immutable class with some property and geter for it
 */
public final class SomeImmutable {
    private String name;
    private int age;

    public SomeImmutable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
