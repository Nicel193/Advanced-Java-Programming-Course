package kukuiev.advjava.labsecond.similartasks.fifthtask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import kukuiev.advjava.labsecond.similartasks.Student;
import kukuiev.advjava.labsecond.similartasks.AcademicGroup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AcademicGroupXML {
    public static void XMLSerialization() {
        XStream xStream = new XStream();
        AcademicGroup academicGroup = new AcademicGroup(Arrays.asList(
                new Student("Ruslan Kukuiev", 19),
                new Student("Vrunich Dunaj", 18),
                new Student("Kakoito Noname", 20)
        ));

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("students", Student.class);

        String xml = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter("./similartasksfiles/AcademicGroup.xml"); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
            System.out.println("Файл сереалізовано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void XMLDeserialization() {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("students", Student.class);

        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File("./similartasksfiles/AcademicGroup.xml"));

        if (academicGroup != null) {
            System.out.println("Файл десеріалізовано");
            System.out.println(academicGroup);
        }
    }

    public static void main(String[] args) {
        System.out.println("Серіалізація й десеріалізація в XML:");
        XMLSerialization();
        XMLDeserialization();
    }
}
