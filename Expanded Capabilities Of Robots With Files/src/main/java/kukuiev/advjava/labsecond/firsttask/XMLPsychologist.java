package kukuiev.advjava.labsecond.firsttask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLPsychologist {
    private PsychologistWithList doctor;

    XMLPsychologist(PsychologistWithList doctor) {
        this.doctor = doctor;
    }

    private void Serialization() {
        XStream xStream = new XStream();

        xStream.alias("doctor", PsychologistWithList.class);
        xStream.alias("receptions", Reception.class);

        String xml = xStream.toXML(doctor);
        try (FileWriter fw = new FileWriter("./firsttaskfiles/Psychologist.xml"); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
            Program.logger.info("Serializing to XML");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Deserialization() throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("doctor", PsychologistWithList.class);
        xStream.alias("receptions", Reception.class);

        doctor = (PsychologistWithList) xStream.fromXML(new File("./firsttaskfiles/Psychologist.xml"));

        if (doctor != null) {
            System.out.println(doctor);
            Program.logger.info("Deserializing from XML");
        }
    }

    public void test() {
        System.out.println("\n=========================================================================");
        System.out.println("Серіалізація й десеріалізація за допомогою XML:");

        try {
            Serialization();
            Deserialization();
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(doctor);
        System.out.println();
    }
}
