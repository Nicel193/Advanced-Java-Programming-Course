package kukuiev.advjava.labsecond.similartasks.fifthtask;

import kukuiev.advjava.labsecond.similartasks.AcademicGroup;
import kukuiev.advjava.labsecond.similartasks.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class AcademicGroupTXT {

    private static final String Path = "./similartasksfiles/AcademicGroup.txt";

    /**
     * Creates a new file and writes text to it
     * @throws IOException
     */
    public static void Serialization() {
        AcademicGroup academicGroup = new AcademicGroup(Arrays.asList(
                new Student("Ruslan Kukuiev", 19),
                new Student("Vrunich Dunaj", 18),
                new Student("Kakoito Noname", 20)
        ));

        try (PrintWriter writer = new PrintWriter(Path)) {
            academicGroup.getStudents().stream()
                    .map(Student::toString)
                    .forEach(writer::println);

            System.out.println("Файл збережено");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Reading data from the file and initializing the object PsychologistWithList
     * @throws IOException
     */
    public static void Deserialization() {

        AcademicGroup academicGroup = new AcademicGroup();

        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            academicGroup = new AcademicGroup(reader.lines()
                    .map(line -> line.split("-"))
                    .map(parts -> new Student(parts[0], Integer.parseInt(parts[1])))
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println("Файл завантажено");
        System.out.println(academicGroup);
    }

    public static void main(String[] args) {
        System.out.println("Серіалізація й десеріалізація за допомогою StreamAPI:");

        Serialization();
        Deserialization();
    }
}
