package kukuiev.advjava.labfourth.firsttask;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class PsychologistWithList extends Doctor {
    private List<Reception> receptions = new ArrayList<>();

    public PsychologistWithList() {
    }

    PsychologistWithList(String surname, int experience) {
        super(surname, experience);
    }

    @Override
    public Reception[] get_receptions() {
        Reception[] rec = new Reception[get_receptions_count()];
        receptions.toArray(rec);
        return rec;
    }

    public void set_reception(int index, Reception reception) {
        receptions.set(index, reception);
    }

    public void set_receptions(Reception[] receptions) {
        this.receptions.clear();

        Arrays.stream(receptions).forEach(r -> {
            this.receptions.add(r);
        });
    }

    public Reception get_last_reception() {
        return receptions.get(receptions.size() - 1);
    }

    @Override
    public int get_receptions_count() {
        return receptions.size();
    }

    @Override
    public void add_reception(Reception new_reception) {
        receptions.add(new_reception);
    }

    @Override
    public void sort_number_of_visitors() {
        receptions = receptions.stream()
                .sorted(Comparator.comparingInt(Reception::get_number_visitors))
                .collect(Collectors.toList());
    }

    @Override
    public void sort_alphabetic_comments() {
        receptions = receptions.stream().sorted().collect(Collectors.toList());
    }
}
