package kukuiev.advjava.labfourth.firsttask;

import kukuiev.advjava.labfourth.firsttask.DB.PsychologistForDB;
import kukuiev.advjava.labfourth.firsttask.DB.Psychologists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;

import static kukuiev.advjava.labfourth.firsttask.DB.DBUtils.*;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class Program{
    public static Logger logger = LogManager.getLogger(Program.class);
    public static String PathToFiles = "./firsttaskfiles/Psychologist";

    public static void main(String[] args) {
        Psychologists psychologists = createPsychologists();
        exportToJSON(psychologists, "Psychologists.json");
        psychologists = importFromJSON("Psychologists.json");
        createConnection();
        if (createDatabase()) {
            addAll(psychologists);
            showAll();
            System.out.println("\nПереписи в Україні за зростанням населення:");
            showSortedByVisitors("Petya");
            System.out.println("\nДодаємо перепис:");
            addReception("Petya", new ReceptionsWithDates(ZonedDateTime.now().minusDays(25), "new comment", 9));
            showAll();
            System.out.println("\nВидаляємо перепис:");
            removeReception("Petya", 9);
            showAll();
            addPsychologist(new PsychologistForDB("Artem", 7));
            addReception("Artem", new ReceptionsWithDates(ZonedDateTime.now().minusDays(52), "new reception", 5));
            showAll();
            System.out.println("\nПошук слова \"new\":");
            findWord("new");
            exportToJSON("CountriesFromDB.json");
            closeConnection();
        }
    }

    public static Psychologists createPsychologists() {
        PsychologistForDB psychologist = new PsychologistForDB();

        psychologist.set_surname("Petya");
        psychologist.set_experience(12);

        psychologist.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(5), "comment1", 5));
        psychologist.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(10), "comment2", 2));
        psychologist.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(65), "comment3", 10));
        psychologist.add_reception(new ReceptionsWithDates(ZonedDateTime.now().plusDays(5), "comment4", 3));
        psychologist.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(30), "comment5", 1));

        Psychologists psychologists = new Psychologists();

        psychologists.getList().add(psychologist);
        return psychologists;
    }
}
