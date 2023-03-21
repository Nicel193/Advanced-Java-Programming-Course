package Task_4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть пароль:");
        String password = scanner.nextLine();

        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-*_]){4,}$");
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            System.out.println("Пароль дійсний.");
        } else {
            System.out.println("Пароль недійсний.");
        }
    }
}
