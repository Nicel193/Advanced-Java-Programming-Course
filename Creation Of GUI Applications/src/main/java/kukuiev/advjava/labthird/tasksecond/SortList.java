package kukuiev.advjava.labthird.tasksecond;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collections;

/**
 * @author Kukuiev Ruslan KN-221A
 **/
public class SortList {
    static void orderList(ObservableList<Double> list) {
        ObservableList<Double> negList = FXCollections.observableArrayList();
        int positiveIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 0) {
                negList.add(list.get(i));
            }
            if (list.get(i) >= 0) {
                Collections.swap(list, i, positiveIndex);
                positiveIndex++;
            }
        }

        for (int i = positiveIndex, j = negList.size() - 1; i < list.size(); i++, j--) {
            list.set(i, negList.get(j));
        }
    }

    public static void main(String[] args) {
        ObservableList<Double> list = FXCollections.observableArrayList();
        list.addListener((ListChangeListener<? super Double>) c -> System.out.println(list));
        list.addAll(-2.7, -4.1, 1.5, 0.8, -3.2, 3.7, -1.9);
        orderList(list);
        list.clear();
    }
}