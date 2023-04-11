package kukuiev.advjava.labsecond.firsttask;

/**
 * Base abstract class representing the doctor object
 */
public abstract class Doctor {
    private String surname;
    private int experience;

    Doctor() {
    }

    Doctor(String surname, int experience) {
        this.surname = surname;
        this.experience = experience;
    }

    /**
     * To get receptions array
     *
     * @return array receptions
     */
    public abstract Reception[] get_receptions();

    /**
     * To get the length of an array of receptions
     *
     * @return length receptions array
     */
    public abstract int get_receptions_count();

    public abstract void set_reception(int index, Reception reception);

    /**
     * To add new reception to array
     *
     * @param new_reception transfer the new reception to the array
     */
    public abstract void add_reception(Reception new_reception);

    public String get_surname() {
        return surname;
    }

    public void set_surname(String surname) {
        this.surname = surname;
    }

    public int get_experience() {
        return experience;
    }

    public void set_experience(int experience) {
        this.experience = experience;
    }


    /**
     * Override for output
     *
     * @return information about the doctor
     */
    @Override
    public String toString() {
        String info = "Surname: \n" + surname + " | Experience: " + experience
                + "\nReceptions: \n";

        for (int i = 0; i < get_receptions_count(); i++) {
            info += (get_receptions()[i] + "\n");
        }

        return info;
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

        Doctor doctor = (Doctor) obj;

        return (this.experience == doctor.get_experience()) && this.surname.equals(doctor.get_surname());
    }

    @Override
    public int hashCode() {
        return experience + surname.hashCode();
    }

    /**
     * To find average number visitors
     *
     * @return average number visitors
     */
    public final int find_average_number_visitors() {
        if (get_receptions_count() == 0) return -1;

        int average_number_visitors = 0;
        for (int i = 0; i < get_receptions_count(); i++) {
            average_number_visitors += get_receptions()[i].get_number_visitors();
        }

        return average_number_visitors / get_receptions_count();
    }

    /**
     * To find min number visitors
     *
     * @return min number visitors
     */
    public final int find_min_number_visitors() {
        if (get_receptions_count() == 0) return -1;

        int min_number_visitors = get_receptions()[0].get_number_visitors();

        for (int i = 1; i < get_receptions_count(); i++) {
            if (min_number_visitors > get_receptions()[i].get_number_visitors()) {
                min_number_visitors = get_receptions()[i].get_number_visitors();
            }
        }

        return min_number_visitors;
    }

    /**
     * To find longest comment
     *
     * @return longest comment
     */
    public final String find_longest_comment() {
        if (get_receptions_count() == 0) return null;

        String longest_comment = get_receptions()[0].get_comment();

        for (int i = 1; i < get_receptions_count(); i++) {
            if (longest_comment.length() < get_receptions()[i].get_comment().length()) {
                longest_comment = get_receptions()[i].get_comment();
            }
        }

        return longest_comment;
    }

    /**
     * Sorting array by increasing the number of visitors
     */
    public void sort_number_of_visitors() {
        for (int out = get_receptions_count() - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (get_receptions()[in].get_number_visitors() > get_receptions()[in + 1].get_number_visitors()) {
                    Reception temp = get_receptions()[in];
                    set_reception(in, get_receptions()[in + 1]);
                    set_reception(in + 1, temp);
                }
            }
        }
    }

    /**
     * Sorting array by comment alphabet
     */
    public void sort_alphabetic_comments() {
        int i;
        Reception key;
        for (int k = 1; k < get_receptions_count(); k++) {
            key = get_receptions()[k];
            i = k - 1;
            while ((i >= 0) && (get_receptions()[i].get_comment().compareTo(key.get_comment()) > 0)) {
                set_reception(i + 1, get_receptions()[i]);
                i = i - 1;
            }
            set_reception(i + 1, key);
        }
    }
}

