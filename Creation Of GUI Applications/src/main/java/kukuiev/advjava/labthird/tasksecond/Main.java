package kukuiev.advjava.labthird.tasksecond;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {
    public static void main(String[] args) {
        // Створюємо ObservableList дійсних чисел
        ObservableList<Double> list = FXCollections.observableArrayList(-2.0, 1.5, 3.2, -4.7, 0.9);

        // Виводимо початковий стан списку
        System.out.println("Початковий список: " + list);

        // Створюємо два списки для додатніх та від'ємних чисел
        ObservableList<Double> positiveList = FXCollections.observableArrayList();
        ObservableList<Double> negativeList = FXCollections.observableArrayList();

        // Розділяємо елементи за знаком
        for (Double num : list) {
            if (num >= 0) {
                positiveList.add(num);
            } else {
                negativeList.add(num);
            }
        }

        // З'єднуємо списки та виводимо новий стан
        list.clear();
        list.addAll(positiveList);
        list.addAll(negativeList);
        System.out.println("Новий список: " + list);
    }
}