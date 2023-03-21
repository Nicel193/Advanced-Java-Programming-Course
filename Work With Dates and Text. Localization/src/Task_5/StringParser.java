package Task_5;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
    public static void main(String[] args) {
        String sentence = "asf1tert2b3c4d56e789f0ghij345";

        if(sentence.length() < 20) {
            System.out.println("Кількість символів в рядку повинна бути більше ніж 20");
            return;
        }

        String[] substrings = sentence.split("\\d+");
        System.out.println(Arrays.toString(substrings));
    }
}
