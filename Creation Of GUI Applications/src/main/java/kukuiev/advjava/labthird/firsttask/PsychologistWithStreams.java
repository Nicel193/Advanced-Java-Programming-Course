package kukuiev.advjava.labthird.firsttask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class PsychologistWithStreams extends PsychologistWithList {

    PsychologistWithStreams() {

    }

    PsychologistWithStreams(String surname, int experience) {
        super(surname, experience);
    }

    /**
     * Finds rows that contain the desired word
     * @param text Keyword
     * @return Sorted receptions
     */
    public Reception[] find_reception_by_comment(String text) {
        return Arrays.stream(get_receptions()).filter((hour -> {
            for (String word : hour.get_comment().split("\\s")) {
                if (word.startsWith(text) || word.endsWith(text)) {
                    return true;
                }
            }
            return false;
        })).toArray(Reception[]::new);
    }

    public int get_shortest_period_days() {
        if (get_receptions_count() < 2) return 0;

        List<ReceptionsWithDates> receptions = new ArrayList<>();
        Arrays.stream(get_receptions()).forEach(r -> {
            receptions.add((ReceptionsWithDates) r);
        });

        receptions.sort((Comparator.comparing(ReceptionsWithDates::get_zone_date).reversed()));
        return Period.between(LocalDate.from(receptions.get(1).get_zone_date()),
                LocalDate.from(receptions.get(0).get_zone_date())).getDays();
    }

    @Override
    public void sort_alphabetic_comments() {
        Reception[] receptions = get_receptions();
        Arrays.sort(receptions, new Comparator<>() {
            Collator collator = Collator.getInstance();

            @Override
            public int compare(Reception o1, Reception o2) {
                return collator.compare(o1.get_comment(), o2.get_comment());
            }
        });
        set_receptions(receptions);
    }

    public PsychologistWithStreams create_psychologist() {
        set_surname("Petya");
        set_experience(12);

        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(5), "comment1", 5));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(10), "comment2", 2));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(65), "comment3", 10));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().plusDays(5), "comment4", 3));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(30), "comment5", 1));

        return this;
    }

    /**
     * Creates a new file and writes text to it
     * @throws IOException
     */
    public static void writeToFileTxt(PsychologistWithStreams doctor, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Program.logger.info("Write to text file");
            writer.write(doctor.toString());
        } catch (IOException e) {
            Program.logger.error(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Reading data from the file and initializing the object PsychologistWithList
     * @throws IOException
     */
    public static PsychologistWithStreams readFromFileTxt(String filename) {
        PsychologistWithStreams doctor = new PsychologistWithStreams();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Program.logger.info("Read from the text file");
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(" ");
                if (parts.length == 5) {
                    doctor = new PsychologistWithStreams(parts[1], Integer.parseInt(parts[4]));
                    Reception[] receptions = parseReception(reader);
                    doctor.set_receptions(receptions);
                }
            }
        } catch (IOException e) {
            Program.logger.error(e.toString());
        }
        return doctor;
    }

    private static Reception[] parseReception(BufferedReader reader) throws IOException {
        List<Reception> receptions = new ArrayList<>();

        while (reader.ready()) {
            reader.readLine();
            String day = reader.readLine().split(": ")[1];
            String comment = reader.readLine().split(": ")[1];
            String visitors = reader.readLine().split(": ")[1];
            reader.readLine();
            receptions.add(new Reception(day, comment, Integer.parseInt(visitors)));
        }

        return receptions.toArray(new Reception[0]);
    }

    public void test() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        System.out.println(this);
    }
}
