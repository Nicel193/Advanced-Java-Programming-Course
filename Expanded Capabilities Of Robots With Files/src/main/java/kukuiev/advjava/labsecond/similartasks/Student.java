package kukuiev.advjava.labsecond.similartasks;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }
}
