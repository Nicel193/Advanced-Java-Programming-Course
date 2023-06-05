package kukuiev.advjava.labfourth.firsttask.DB;

import kukuiev.advjava.labfourth.firsttask.*;

public class ReseptionForDB extends Reception {
    private long id = -1;

    /**
     * Повертає ID перепису
     *
     * @return ID перепису
     */
    public long getId() {
        return id;
    }

    /**
     * Встановлює ID перепису
     *
     * @param id ID перепису
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Конструктор ініціалізує об'єкт вказаними значеннями
     */
    public ReseptionForDB(String day, String comment, int number_visitors) {
        super(day, comment, number_visitors);
    }
}
