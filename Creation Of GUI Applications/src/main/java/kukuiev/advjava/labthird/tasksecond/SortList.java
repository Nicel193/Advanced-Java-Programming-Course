import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Kukuiev Ruslan KN-221A
 **/
public class SortList {
    public static void main(String[] args) {
        ObservableList<Double> list = FXCollections.observableArrayList(-3.2, 1.5, -2.7, 0.8, -4.1, 3.7, -1.9);
        System.out.println("Початковий список: " + list);

        ObservableList<Double> positives = FXCollections.observableArrayList();
        ObservableList<Double> negatives = FXCollections.observableArrayList();
        for (Double number : list) {
            if (number >= 0) {
                positives.add(number);
                System.out.println("Список додатніх чисел" + positives.toString());
            } else {
                negatives.add(number);
                System.out.println("Список від'ємних чисел" + negatives.toString());
            }
        }

        FXCollections.reverse(negatives);
        System.out.println("Міняємо порядок елементів від'ємного списку" + negatives.toString());

        ObservableList<Double> sortedList = FXCollections.observableArrayList();
        sortedList.addAll(positives);
        sortedList.addAll(negatives);
        System.out.println("Змінений список: " + sortedList);
    }
}