package Task_3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KyivstarPhoneNumberValidator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%nВведіть номер телефону: ");
        String phone = scan.nextLine();
        Pattern p = Pattern.compile("^(\\+?(38))?(0(67|68|9[6-8]))\\w{7}$");
        Matcher m = p.matcher(phone);
        if (m.matches()) {
            System.out.println("\"" + phone + "\" - правильний формат номеру");
        }
        else {
            System.out.println("\"" + phone + "\" - хибний формат номеру");
        }
    }
}
