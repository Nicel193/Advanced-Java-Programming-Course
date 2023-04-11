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

public class AcademicGroupJSON {
    public static void JSONSerialization() {
        XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
        AcademicGroup academicGroup = new AcademicGroup(Arrays.asList(
                new Student("Ruslan Kukuiev", 19),
                new Student("Vrunich Dunaj", 18),
                new Student("Kakoito Noname", 20)
        ));

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("students", Student.class);

        String json = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter("./similartasksfiles/AcademicGroup.json"); PrintWriter out = new PrintWriter(fw)) {
            out.println(json);
            System.out.println("Файл сереалізовано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void JSONDeserialization() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.addImplicitCollection(AcademicGroup.class, "students");
        xStream.alias("students", Student.class);

        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File("./similartasksfiles/AcademicGroup.json"));

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
