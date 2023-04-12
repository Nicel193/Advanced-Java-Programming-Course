package kukuiev.advjava.labsecond.thirdtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class DivisorsFinderTest {
    @Test
    public void testFindDivisors() {
        int[] expected = {1, 2, 3, 4, 6, 12};
        int[] actual = DivisorsFinder.findDivisors(12);
        assertArrayEquals(expected, actual);

        int[] expected2 = {1, 2, 7, 14};
        int[] actual2 = DivisorsFinder.findDivisors(14);
        assertArrayEquals(expected2, actual2);
    }
}
