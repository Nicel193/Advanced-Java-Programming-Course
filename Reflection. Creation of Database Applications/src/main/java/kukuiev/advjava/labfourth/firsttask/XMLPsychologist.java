package kukuiev.advjava.labfourth.firsttask;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class XMLPsychologist {
    /**
     * Creates a new file and writes text to it
     * @throws IOException
     */
    public static void Serialization(PsychologistWithList doctor, String filePath) throws IOException {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        String xml = xstream.toXML(doctor);
        Files.writeString(new File(filePath).toPath(), xml);
        Program.logger.info("Serializing to XML");
    }

    /**
     * Reading data from the file and initializing the object PsychologistWithList
     * @throws IOException
     */
    public static PsychologistWithList Deserialization(String filePath) throws IOException {
        PsychologistWithList doctor = new PsychologistWithList();

        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        String xml = Files.readString(new File(filePath).toPath());
        doctor = (PsychologistWithList) xstream.fromXML(xml);
        Program.logger.info("Deserializing from XML");

        return doctor;
    }
}
