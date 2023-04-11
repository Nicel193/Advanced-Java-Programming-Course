package kukuiev.advjava.labfirst.taskthird;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks if the number is Kyivstar
 *
 * @author Kukuiev Ruslan
 */
public class KyivstarPhoneNumberValidator {
    public static void main(String[] args) {
        String[] phones = {"+380678092789", "345432", "0988092789", "+389580927897", "380678092789", "067ddddddd"};
        Pattern p = Pattern.compile("^(\\+(38))?(0(67|68|9[6-8]))\\d{7}$");

        for (int i = 0; i < phones.length; i++) {
            Matcher m = p.matcher(phones[i]);
            if (m.matches()) {
                System.out.println("\"" + phones[i] + "\" - правильний формат номеру");
            } else {
                System.out.println("\"" + phones[i] + "\" - хибний формат номеру");
            }
        }
    }
}
