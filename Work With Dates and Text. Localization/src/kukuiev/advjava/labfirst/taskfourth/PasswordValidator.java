package kukuiev.advjava.labfirst.taskfourth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks if the password is correct
 *
 * @author Kukuiev Ruslan
 */
public class PasswordValidator {
    public static void main(String[] args) {
        String[] passwords = {"kf-d5Ff", "gd45d-df", "df-Fds4", "he#F1"};

        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-*_])[a-zA-Z0-9-*_]{4,}$");

        for (int i = 0; i < passwords.length; i++) {
            Matcher matcher = pattern.matcher(passwords[i]);
            System.out.print(passwords[i]);
            if (matcher.matches()) {
                System.out.println(" - Пароль дійсний.");
            } else {
                System.out.println(" - Пароль недійсний.");
            }
        }
    }
}
