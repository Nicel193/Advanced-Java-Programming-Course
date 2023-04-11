package kukuiev.advjava.labsecond.thirdtask;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DivisorsFinder {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDivisors(14)));
    }

    public static int[] findDivisors(int number) {
        return IntStream.rangeClosed(1, number)
                .filter(i -> number % i == 0)
                .toArray();
    }
}
