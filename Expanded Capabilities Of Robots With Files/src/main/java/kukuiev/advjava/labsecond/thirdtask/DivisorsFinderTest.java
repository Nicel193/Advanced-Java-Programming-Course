package kukuiev.advjava.labsecond.thirdtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DivisorsFinderTest {
    @Test
    public void testFindDivisors() {
        int[] expected = {1, 2, 3, 4, 6, 12};
        int[] actual = DivisorsFinder.findDivisors(12);
        assertArrayEquals(expected, actual);
    }
}
