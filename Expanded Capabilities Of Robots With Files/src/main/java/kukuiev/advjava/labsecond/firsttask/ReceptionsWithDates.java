package kukuiev.advjava.labsecond.firsttask;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReceptionsWithDates extends Reception {
    private ZonedDateTime reception_date;

    ReceptionsWithDates(ZonedDateTime reception_date, String comment, int number_visitors) {
        super(null, comment, number_visitors);

        this.reception_date = reception_date;
        set_day(reception_date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.getDefault())));
    }

    public ZonedDateTime get_zone_date() {
        return reception_date;
    }

    @Override
    public String get_comment() {
        ResourceBundle bundle = ResourceBundle.getBundle("receptions");
        return bundle.getString(super.get_comment());
    }

    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("receptions");
        return "------------------------------------------\n"
                + bundle.getString("dayLocalization") + ": " + get_day() + "\n"
                + bundle.getString("commentLocalization") + ": " + get_comment() + "\n"
                + bundle.getString("numberVisitorsLocalization") + ": " + get_number_visitors()
                + "\n------------------------------------------";
    }
}
