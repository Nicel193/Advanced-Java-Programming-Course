package kukuiev.advjava.labthird.firsttask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class DoctorController implements Initializable {

    private PsychologistWithList doctor;

    // Список, вміст якого відображатиметься в таблиці:
    private ObservableList<Reception> observableList;

    @FXML
    private TableView<Reception> tableViewCensuses;

    @FXML
    private TextField textFieldText;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TableColumn<Reception, Integer> tableColumnCountVisitors;

    @FXML
    private TableColumn<Reception, String> tableColumnDate;

    @FXML
    private TableColumn<Reception, String> tableColumnComments;

    @FXML
    private TextField textFieldExperience;

    @FXML
    private TextArea textAreaResults;

    @FXML
    void nameChanged(ActionEvent event) {
        doctor.set_surname(textFieldSurname.getText());
    }

    @FXML
    void experienceChanged(ActionEvent event) {
        doctor.set_experience(Integer.parseInt(textFieldExperience.getText()));
    }

    @FXML
    void doSearchByWord(ActionEvent event) {
        textAreaResults.setText("");

        if(doctor == null) return;

        for (int i = 0; i < doctor.get_receptions_count(); i++) {
            Reception c = (Reception) doctor.get_receptions()[i];
            if (c.containsWord(textFieldText.getText())) {
                showResults(c);
            }
        }
    }

    @FXML
    void doSearchBySubstring(ActionEvent event) {
        textAreaResults.setText("");

        if(doctor == null) return;

        for (int i = 0; i < doctor.get_receptions_count(); i++) {
            Reception c = (Reception) doctor.get_receptions()[i];
            if (c.containsSubstring(textFieldText.getText())) {
                showResults(c);
            }
        }
    }

    private void showResults(Reception census) {
        textAreaResults.appendText("Дата: " + census.getDay()+ "\n");
        textAreaResults.appendText("Коментар: " + census.getComment() + "\n");
        textAreaResults.appendText("\n");
    }

    @FXML
    void doNew(ActionEvent event) {
        doctor = new PsychologistWithStreams();
        observableList = null;
        textFieldSurname.setText("");
        textFieldExperience.setText("");
        textFieldText.setText("");
        textAreaResults.setText("");
        tableViewCensuses.setItems(null);
        tableViewCensuses.setPlaceholder(new Label(""));
    }

    /**
     * Створення діалогового вікна вибору файлів
     *
     * @param title - текст заголовку вікна
     */
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        // Починаємо шукати з поточної теки:
        fileChooser.setInitialDirectory(new File("."));
        // Встановлюємо фільтри для пошуку файлів:
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML-файли (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Усі файли (*.*)", "*.*"));
        // Вказуємо заголовок вікна:
        fileChooser.setTitle(title);
        return fileChooser;
    }

    /**
     * Діалогове вікно довільного повідомлення
     *
     * @param message - текст повідомлення
     */
    public static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Діалогове вікно повідомлення про помилку
     *
     * @param message - текст повідомлення
     */
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @FXML
    void doOpen(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Відкрити XML-файл");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                doctor = XMLPsychologist.Deserialization(file.getCanonicalPath());
                // Заповнюємо текстові поля прочитаними даними:
                textFieldSurname.setText(doctor.get_surname());
                textFieldExperience.setText(String.valueOf(doctor.get_experience()));
                textAreaResults.setText("");
                // Очищаємо та оновлюємо таблицю:
                tableViewCensuses.setItems(null);
                updateTable();
            } catch (IOException e) {
                showError("Файл не знайдено");
            } catch (Exception e) {
                showError("Неправильний формат файлу");
            }
        }
    }

    @FXML
    void doSave(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Зберегти XML-файл");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                nameChanged(event);
                experienceChanged(event);
                XMLPsychologist.Serialization(doctor, file.getCanonicalPath());
                showMessage("Результати успішно збережені");
            } catch (Exception e) {
                showError("Помилка запису в файл");
            }
        }
    }

    @FXML
    void doExit(ActionEvent event) {
        Platform.exit(); // коректне завершення застосунку JavaFX
    }

    @FXML
    void doAdd(ActionEvent event) {
        doctor.add_reception(new Reception("", "", 0));
        updateTable(); // створюємо нові дані
    }

    @FXML
    void doRemove(ActionEvent event) {
        // Не можемо видалити рядок, якщо немає даних:
        if (observableList == null) return;

        Reception[] receptions = doctor.get_receptions();
        if (receptions.length < 0) return;

        doctor.set_receptions(Arrays.copyOfRange(receptions, 0, receptions.length - 1));
        updateTable();
    }

    @FXML
    void doSortByVisitors(ActionEvent event) {
        doctor.sort_number_of_visitors();
        updateTable();
    }

    @FXML
    void doSortByComments(ActionEvent event) {
        doctor.sort_alphabetic_comments();
        updateTable();
    }

    @FXML
    void doAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Про програму...");
        alert.setHeaderText("Дані про записи до лікаря");
        alert.setContentText("Версія 1.0");
        alert.showAndWait();
    }

    private void updateTable() {
        // Заповнюємо observableList:
        List<Reception> receptions = Arrays.stream(doctor.get_receptions()).toList();
        observableList = FXCollections.observableList(receptions);
        tableViewCensuses.setItems(observableList);

        // Вказуємо для колонок зв'язану з ними властивість і механізм редагування
        tableColumnCountVisitors.setCellValueFactory(new PropertyValueFactory<>("number_visitors"));
        tableColumnCountVisitors.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableColumnCountVisitors.setOnEditCommit(t -> updateCountVisitors(t));

        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("day"));
        tableColumnDate.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDate.setOnEditCommit(t -> updateDate(t));

        tableColumnComments.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableColumnComments.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnComments.setOnEditCommit(t -> updateComment(t));
    }

    private void updateComment(TableColumn.CellEditEvent<Reception, String> t) {
        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setComment(t.getNewValue());
    }

    private void updateDate(TableColumn.CellEditEvent<Reception, String> t) {
        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setDay(t.getNewValue());
    }

    private void updateCountVisitors(TableColumn.CellEditEvent<Reception, Integer> t) {
        Reception c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setNumber_visitors(t.getNewValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewCensuses.setPlaceholder(new Label(""));

        Pattern validIntegerPattern = Pattern.compile("\\d{0,2}"); // Паттерн для цілих чисел
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (validIntegerPattern.matcher(newText).matches()) { // Перевіряємо на довжину
                return change;
            }
            return null;
        };
        TextFormatter<Integer> integerTextFormatter = new TextFormatter<>(new IntegerStringConverter(), null, integerFilter);
        textFieldExperience.setTextFormatter(integerTextFormatter);
    }
}
