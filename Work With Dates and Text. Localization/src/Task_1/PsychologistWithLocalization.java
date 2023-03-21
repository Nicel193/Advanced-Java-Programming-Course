package Task_1;

import java.text.Collator;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.*;

public class PsychologistWithLocalization extends PsychologistWithList {
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

    public PsychologistWithLocalization create_psychologist() {
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
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PsychologistWithLocalization().create_psychologist().test();
        Locale.setDefault(new Locale("uk"));
        new PsychologistWithLocalization().create_psychologist().test();
    }
}
