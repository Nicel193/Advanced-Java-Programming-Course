package kukuiev.advjava.labfirst.tasksecond;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;

/**
 * Checks if the date is correct
 *
 * @author Kukuiev Ruslan
 */
public class DateOutput {
    public static void main(String[] args) {
        String[] dateString = {"05.03.2022", "5306.2005", ".03.2017", "29.02.2023", "29.02.2023"};

        Pattern pattern = Pattern.compile("^(\\d{2})\\.(\\d{2})\\.(\\d{4})$");

        for (int i = 0; i < dateString.length; i++) {
            Matcher matcher = pattern.matcher(dateString[i]);
            System.out.println("(" + dateString[i] + "):");
            if (matcher.matches()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int year = Integer.parseInt(matcher.group(3));

                try {
                    Calendar calendar = new GregorianCalendar(year, month - 1, day);
                    Date date = new Date(year - 1900, month - 1, day);
//                    Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString[i]);
                    LocalDate localDate = LocalDate.of(year, month, day);
                    System.out.println("Об'єкт Date: " + date);
                    System.out.println("Об'єкт GregorianCalendar: " + calendar.getTime());
                    System.out.println("Об'єкт LocalDate: " + localDate + "\n");
                } catch (Exception e) {
                    System.out.println(e + "\n");
                }
            } else {
                System.out.println("Invalid date format\n");
            }
        }
    }
}
