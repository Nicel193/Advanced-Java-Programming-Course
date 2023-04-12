package kukuiev.advjava.labsecond.similartasks.sixthtask;

import kukuiev.advjava.labsecond.similartasks.Student;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import kukuiev.advjava.labsecond.similartasks.AcademicGroup;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class AcademicGroupOrgJSON {
    private static final String Path = "./similartasksfiles/AcademicGroupOrg.json";

    public static void JSONSerialization() throws JSONException, IOException {
        AcademicGroup academicGroup = new AcademicGroup(Arrays.asList(
                new Student("Ruslan Kukuiev", 19),
                new Student("Vrunich Dunaj", 18),
                new Student("Kakoito Noname", 20)
        ));

        JSONObject academicGroupWrapper = new JSONObject();
        JSONObject academicGroupElement = new JSONObject();
        JSONArray studentsElements = new JSONArray();

        for (Student student : academicGroup.getStudents()) {
            JSONObject studentElement = new JSONObject();
            studentElement.put("name", student.getName());
            studentElement.put("age", student.getAge());
            studentsElements.put(studentElement);
        }
        academicGroupElement.put("students", studentsElements);
        academicGroupWrapper.put("academicGroup", academicGroupElement);

        try (FileWriter file = new FileWriter(Path)) {
            file.write(academicGroupWrapper.toString());
            System.out.println("Файл сереалізовано");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void JSONDeserialization() throws JSONException, IOException {
        String json = new String(Files.readAllBytes(Paths.get(Path)));
        JSONObject jsonElement = new JSONObject(json);
        AcademicGroup academicGroup = new AcademicGroup();

        JSONArray studentElements = jsonElement.getJSONObject("academicGroup").getJSONArray("students");
        for (int i = 0; i < studentElements.length(); i++) {
            JSONObject studentElement = studentElements.getJSONObject(i);
            Student student = new Student(studentElement.getString("name"), studentElement.getInt("age"));
            academicGroup.addStudent(student);
        }

        if (academicGroup != null) {
            System.out.println("Файл десеріалізовано");
            System.out.println(academicGroup);
        }
    }

    public static void main(String[] args) {
        System.out.println("Серіалізація й десеріалізація в Org.JSON:");

        try {
            JSONSerialization();
            JSONDeserialization();
        } catch (JSONException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
