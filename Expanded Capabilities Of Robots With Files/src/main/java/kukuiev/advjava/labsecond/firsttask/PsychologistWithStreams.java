package kukuiev.advjava.labsecond.firsttask;

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

public class PsychologistWithStreams extends PsychologistWithList {

    PsychologistWithStreams() {

    }

    PsychologistWithStreams(String surname, int experience) {
        super(surname, experience);
    }

    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("receptions");
        StringBuilder result = new StringBuilder(bundle.getString("surnameLocalization")
                + ": " + bundle.getString("surname") + " | "
                + bundle.getString("experienceLocalization") + ": " + get_experience());

        for (int i = 0; i < get_receptions_count(); i++) {
            result.append("\n").append(get_receptions()[i]);
        }

        return result.toString();
    }

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
        ResourceBundle bundle = ResourceBundle.getBundle("receptions");
        set_surname(bundle.getString("surname"));
        set_experience(12);

        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(5), "comment1", 5));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(10), "comment2", 2));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(65), "comment3", 10));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().plusDays(5), "comment4", 3));
        add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(30), "comment5", 1));

        return this;
    }

    public static void writeToFileTxt(PsychologistWithStreams doctor, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Program.logger.info("Write to text file");
            writer.write(doctor.toString());
        } catch (IOException e) {
            Program.logger.error(e.toString());
            e.printStackTrace();
        }
    }

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
        ResourceBundle bundle = ResourceBundle.getBundle("receptions");
        NumberFormat numberFormat = NumberFormat.getInstance();

        System.out.println("\n===================== " + bundle.getString("languageLocalization")
                + ": " + Locale.getDefault() + " =====================");
        System.out.println(this);

        System.out.println("\n===================== " + bundle.getString("sortByAlphabetic") + " =====================");
        sort_alphabetic_comments();
        Arrays.stream(get_receptions()).forEach((reception -> System.out.println(reception.get_comment())));

        System.out.println("\n===================== " + bundle.getString("SortByIncrease") + " =====================");
        sort_number_of_visitors();
        Arrays.stream(get_receptions()).forEach((reception ->
                System.out.println(numberFormat.format(reception.get_number_visitors()) + " -> " + reception.get_comment())));

        System.out.println("\n===================== " + bundle.getString("wordLookingForLocalization") + ": "
                + bundle.getString("findWord") + " =====================");
        System.out.println(bundle.getString("findHoursByCommentLocalization") + ":");
        Arrays.stream(find_reception_by_comment(bundle.getString("findWord"))).forEach((reception -> System.out.println(reception.get_comment())));


        System.out.println("\n=========================================================================");
        System.out.println(bundle.getString("ShortestPeriod") + ": " + numberFormat.format(get_shortest_period_days()));
        System.out.println(bundle.getString("averageNumberLocalization") + ": " + numberFormat.format(find_average_number_visitors()));
        System.out.println(bundle.getString("minimumNumberVisitorsLocalization") + ": " + numberFormat.format(find_min_number_visitors()));
        System.out.println(bundle.getString("longestCommentLocalization") + ": " + find_longest_comment());

        System.out.println("\n=========================================================================");
        System.out.println("Серіалізація й десеріалізація за допомогою TXT:");
        writeToFileTxt(this, "firsttaskfiles/Psychologist.txt");
        System.out.println((PsychologistWithStreams)readFromFileTxt("firsttaskfiles/Psychologist.txt"));

        new XMLPsychologist(this).test();
        new JSONPsychologist(this).test();
    }
}
