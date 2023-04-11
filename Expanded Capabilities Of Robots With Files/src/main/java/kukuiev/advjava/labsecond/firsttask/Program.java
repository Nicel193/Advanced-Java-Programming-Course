package kukuiev.advjava.labsecond.firsttask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class Program {
    public static Logger logger = LogManager.getLogger(Program.class);

    public static void main(String[] args) {
        Program.logger.info("Program started");

        Locale.setDefault(new Locale("en"));
        new PsychologistWithStreams().create_psychologist().test();
        Locale.setDefault(new Locale("uk"));
        new PsychologistWithStreams().create_psychologist().test();
    }

}
