package Task_2;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;


public class DateOutput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть дату у форматі dd.mm.yyyy: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("^(\\d{2})\\.(\\d{2})\\.(\\d{4})$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            Calendar calendar = new GregorianCalendar(year, month-1, day);
            Date date = calendar.getTime();
            LocalDate localDate = LocalDate.of(year, month, day);
            System.out.println("Об'єкт Date: " + date);
            System.out.println("Об'єкт GregorianCalendar: " + calendar.getTime());
            System.out.println("Об'єкт LocalDate: " + localDate);
        } else {
            System.out.println("Invalid date format");
        }
    }
}
