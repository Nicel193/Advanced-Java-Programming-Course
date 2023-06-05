package kukuiev.advjava.labfourth.firsttask.DB;

import kukuiev.advjava.labfourth.firsttask.PsychologistWithStreams;

public class PsychologistForDB extends PsychologistWithStreams {
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
     * Конструктор ініціалізує об'єкт усталеними значеннями
     */
    public PsychologistForDB() {
    }

    /**
     * Конструктор ініціалізує об'єкт вказаними значеннями
     */
    public PsychologistForDB(String surname, int experience) {
        super(surname, experience);
    }
}
