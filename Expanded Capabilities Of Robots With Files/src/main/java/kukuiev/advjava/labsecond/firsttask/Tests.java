package kukuiev.advjava.labsecond.firsttask;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class Tests {

    private PsychologistWithList initDoctor() {
        PsychologistWithList psychologistWithStream = new PsychologistWithList();

        psychologistWithStream.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(5), "comment1", 5));
        psychologistWithStream.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(10), "comment2", 2));
        psychologistWithStream.add_reception(new ReceptionsWithDates(ZonedDateTime.now().minusDays(65), "comment3", 10));

        return psychologistWithStream;
    }

    @Test
    void sort_number_of_visitors() {
        PsychologistWithList psychologistWithStream = initDoctor();

        psychologistWithStream.sort_number_of_visitors();

        assertEquals(psychologistWithStream.get_receptions()[0].get_number_visitors(), 2);
        assertEquals(psychologistWithStream.get_receptions()[1].get_number_visitors(), 5);
        assertEquals(psychologistWithStream.get_receptions()[2].get_number_visitors(), 10);
    }

    @Test
    void test_find_min_number_visitors() {
        PsychologistWithList psychologistWithStream = initDoctor();
        int min = psychologistWithStream.find_min_number_visitors();
        assertEquals(min, 2);
    }

    @Test
    void test_set_day(){
        Reception reception = new Reception("", "", 5);
        reception.set_day("05.02.2007");
        assertEquals(reception.get_day(), reception.get_day());
    }
}
