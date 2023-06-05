package kukuiev.advjava.labfourth.firsttask;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class ReceptionsWithDates extends Reception {
    private ZonedDateTime reception_date;

    public ReceptionsWithDates(ZonedDateTime reception_date, String comment, int number_visitors) {
        super(null, comment, number_visitors);

        this.reception_date = reception_date;
        set_day(reception_date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.getDefault())));
    }

    public ZonedDateTime get_zone_date() {
        return reception_date;
    }
}
