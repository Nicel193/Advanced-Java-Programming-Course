package kukuiev.advjava.labsecond.firsttask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class JSONPsychologist {

    private PsychologistWithList doctor;

    JSONPsychologist(PsychologistWithList doctor) {
        this.doctor = doctor;
    }

    /**
     * Creates a new file and writes text to it
     * @throws IOException
     */
    private void Serialization() throws IOException{
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = xstream.toXML(doctor);
        Files.writeString(new File(Program.PathToFiles + ".json").toPath(), json);
        Program.logger.info("Serializing to JSON");
    }

    /**
     * Reading data from the file and initializing the object PsychologistWithList
     * @throws IOException
     */
    private void Deserialization() throws IOException{
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = Files.readString(new File(Program.PathToFiles + ".json").toPath());
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
