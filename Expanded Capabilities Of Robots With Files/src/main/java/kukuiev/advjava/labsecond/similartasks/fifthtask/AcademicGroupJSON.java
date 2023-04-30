package kukuiev.advjava.labsecond.similartasks.fifthtask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import kukuiev.advjava.labsecond.similartasks.AcademicGroup;
import kukuiev.advjava.labsecond.similartasks.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class AcademicGroupJSON {

    private static final String Path = "./similartasksfiles/AcademicGroup.json";

    /**
     * Creates a new file and writes text to it
     * @throws IOException
     */
    public static void JSONSerialization() {
        XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
        AcademicGroup academicGroup = new AcademicGroup(Arrays.asList(
                new Student("Kukuiev Ruslan", 19),
                new Student("Kostuchenko Dunaj", 18),
                new Student("Shulgenko Igor", 20)
        ));

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("students", Student.class);

        String json = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter(Path); PrintWriter out = new PrintWriter(fw)) {
            out.println(json);
            System.out.println("Файл сереалізовано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reading data from the file and initializing the object PsychologistWithList
     * @throws IOException
     */
    public static void JSONDeserialization() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.addImplicitCollection(AcademicGroup.class, "students");
        xStream.alias("students", Student.class);

        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File(Path));

        if (academicGroup != null) {
            System.out.println("Файл десеріалізовано");
            System.out.println(academicGroup);
        }
    }

    public static void main(String[] args) {
        System.out.println("Серіалізація й десеріалізація в JSON:");
        JSONSerialization();
        JSONDeserialization();
    }
}
