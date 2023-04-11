package kukuiev.advjava.labfirst.tasksixth;

import java.math.BigInteger;
import java.util.Random;

/**
 * Generates BigInteger and calculates "pow" Two ways
 *
 * @author Kukuiev Ruslan
 */
public class GeneratorBigInteger {
    public static void main(String[] args) {
        Random random = new Random();

        BigInteger randomNumber = new BigInteger(100, random);
        int exponent = random.nextInt(5);

        BigInteger result1 = randomNumber.pow(exponent);

        BigInteger result2 = BigInteger.ONE;
        for (int i = 0; i < exponent; i++) {
            result2 = result2.multiply(randomNumber);
        }

        System.out.println("Результат з використанням функції pow класу:");
        System.out.println(result1);
        System.out.println("Результат з використанням функції:");
        System.out.println(result2);

        if (result1.equals(result2)) {
            System.out.println("Результати співпадають");
        } else {
            System.out.println("Результати не співпадають");
        }
    }
}
