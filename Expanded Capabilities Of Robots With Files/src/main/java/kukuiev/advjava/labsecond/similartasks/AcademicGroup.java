package kukuiev.advjava.labsecond.similartasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class AcademicGroup {
    private List<Student> students;

    public AcademicGroup() {
        this.students = new ArrayList<>();
    }

    public AcademicGroup(List<Student> students) {
        this.students = new ArrayList<>();
        students.stream().forEach(s -> this.addStudent(s));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return students.stream().map(Student::toString)
                .collect(Collectors.joining(", "));
    }
}
