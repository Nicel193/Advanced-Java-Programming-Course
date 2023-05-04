package kukuiev.advjava.labthird.firsttask.GUI;

import javafx.scene.control.TableColumn;
import kukuiev.advjava.labthird.firsttask.Reception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTable {
    public void updateComment(TableColumn.CellEditEvent<Reception, String> t) {
        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setComment(t.getNewValue());
    }

    public void updateDate(TableColumn.CellEditEvent<Reception, String> t) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);

        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        String dateValue = t.getNewValue();

        try {
            Date date = format.parse(dateValue);
            c.setDay(dateValue);
        }catch (ParseException e){
            PopUpWindow.showError("Введіть коректно дату: dd.MM.yyyy");
            t.getTableView().refresh();
        }
    }

    public void updateCountVisitors(TableColumn.CellEditEvent<Reception, Integer> t) {
        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setNumber_visitors(t.getNewValue());
    }
}
