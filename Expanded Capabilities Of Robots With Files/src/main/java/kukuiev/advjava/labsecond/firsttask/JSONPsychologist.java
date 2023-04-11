package kukuiev.advjava.labsecond.firsttask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JSONPsychologist {

    private PsychologistWithList doctor;

    JSONPsychologist(PsychologistWithList doctor) {
        this.doctor = doctor;
    }

    private void Serialization() throws IOException{
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = xstream.toXML(doctor);
        Files.writeString(new File("./firsttaskfiles/Psychologist.json").toPath(), json);
        Program.logger.info("Serializing to JSON");
    }

    private void Deserialization() throws IOException{
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = Files.readString(new File("./firsttaskfiles/Psychologist.json").toPath());
        doctor = (PsychologistWithList) xstream.fromXML(json);
        Program.logger.info("Deserializing from JSON");
    }

    public void test() {
        System.out.println("\n=========================================================================");
        System.out.println("Серіалізація й десеріалізація за допомогою JSON:");
        try {

            Serialization();
            Deserialization();
        }catch (IOException e){
            System.err.println(e);
        }

        System.out.println(doctor);
        System.out.println();
    }
}
