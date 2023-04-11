package kukuiev.advjava.labfirst.taskfifth;

import java.util.Arrays;

/**
 * Breaks a string using numbers into an array of characters
 *
 * @author Kukuiev Ruslan
 */
public class StringParser {
    public static void main(String[] args) {
        String sentence = "asf1tert2b3c4d56e789f0ghij345";

        if (sentence.length() < 20) {
            System.out.println("Кількість символів в рядку повинна бути більше ніж 20");
            System.exit(-1);
        }

        String[] substrings = sentence.split("\\d+");

        Arrays.stream(substrings).forEach(r -> {
            if (!r.isEmpty()) System.out.println(r);
        });
    }
}
