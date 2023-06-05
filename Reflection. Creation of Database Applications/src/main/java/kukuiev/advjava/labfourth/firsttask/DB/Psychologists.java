package kukuiev.advjava.labfourth.firsttask.DB;

import java.util.ArrayList;
import java.util.List;

public class Psychologists {
    private List<PsychologistForDB> list = new ArrayList<>();

    /**
     * Повертає список країн (java.util.List)
     * @return список країн
     */
    public List<PsychologistForDB> getList() {
        return list;
    }

    /**
     * Надає подання об'єкта у вигляді рядка
     *
     * @return представлення списку у вигляді рядка
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
