package kukuiev.advjava.labthird.firsttask;

import java.util.regex.Pattern;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class Reception implements Comparable<Reception> {
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String day;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

    public int getNumber_visitors() {
        return number_visitors;
    }

    public void setNumber_visitors(int number_visitors) {
        this.number_visitors = number_visitors;
    }

    private int number_visitors;

    public Reception(String day, String comment, int number_visitors) {
        this.day = day;
        this.comment = comment;
        this.number_visitors = number_visitors;
    }

    public int get_number_visitors() {
        return number_visitors;
    }

    public String get_day() {
        return day;
    }

    public String get_comment() {
        return comment;
    }

    public void set_day(String day) {
        this.day = day;
    }

    public void set_number_visitors(int number_visitors) {
        this.number_visitors = number_visitors;
    }

    public boolean isEmpty() {
        return number_visitors == 0 || day.isEmpty() || comment.isEmpty();
    }

    /**
     * We redefine the object comparison method
     *
     * @param obj the received object
     * @return true - if the objects are equal and otherwise - false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        Reception reception = (Reception) obj;

        return this.number_visitors == reception.number_visitors && comment.equals(reception.comment) && day.equals(reception.day);
    }

    public Boolean containsWord(String rightWord) {
        return Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS)
                .splitAsStream(getComment())
                .anyMatch(word -> word.equals(rightWord));
    }

    public Boolean containsSubstring(String rightWord) {
        return getComment().contains(rightWord);
    }

    /**
     * Override for output
     *
     * @return information about the doctor
     */
    @Override
    public String toString() {
        return "Number visitors: " + number_visitors + " | Comment: " + comment + " | Day: " + day;
    }

    @Override
    public int hashCode() {
        return number_visitors + day.hashCode() + comment.hashCode();
    }

    /**
     * Compares the number of visitors
     *
     * @param o the object to be compared.
     * @return the difference between the number of visitors
     */
    @Override
    public int compareTo(Reception o) {
        return get_number_visitors() - o.get_number_visitors();
    }
}
