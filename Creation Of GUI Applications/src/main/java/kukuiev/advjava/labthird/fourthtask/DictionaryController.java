package kukuiev.advjava.labthird.fourthtask;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Kukuiev Ruslan KN-221A
 **/
public class DictionaryController extends Application {

    @FXML
    private TableView<Map.Entry<String, String>> DictionaryTable;

    @FXML
    private TableColumn<String, String> uaColumn;

    @FXML
    private TableColumn<String, String> enColumn;

    @FXML
    public TextField ua;

    @FXML
    public TextField en;

    @FXML
    public TextField findWordField;

    private Map<String, String> dictionary = new HashMap<>();
    private ObservableList<Map.Entry<String, String>> tableData;

    @FXML
    public void initialize() {
        TableColumn<Map.Entry<String, String>, String> uaCol = new TableColumn<>("Українська");
        uaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, String>, String> enCol = new TableColumn<>("English");
        enCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        DictionaryTable.getColumns().addAll(uaCol, enCol);

        DictionaryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableData = FXCollections.observableArrayList(dictionary.entrySet());
        DictionaryTable.setItems(tableData);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Переписи населення");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void AddWords(javafx.event.ActionEvent actionEvent) {
        if (ua.getText().isEmpty() || en.getText().isEmpty()) {
            ShowError("Не може бути пусте поле");
            return;
        }

        if (!en.getText().matches("[а-яА-ЯІіЇїЄєҐґ']+")) {
            ShowError(en.getText() + " - Не є українським словом");
        }

        if (!ua.getText().matches("[a-zA-Z]+")){
            ShowError(ua.getText() + " - Не є англійськи словом");
        }

        DictionaryTable.getSelectionModel().clearSelection();
        dictionary.put(ua.getText(), en.getText());
        tableData.setAll(dictionary.entrySet());
    }

    public void SearchWord(ActionEvent actionEvent) {
        DictionaryTable.getSelectionModel().clearSelection();

        String keyword = findWordField.getText();

        DictionaryTable.getSelectionModel().clearSelection();

        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).getKey().equals(keyword) || tableData.get(i).getValue().equals(keyword)) {
                DictionaryTable.getSelectionModel().select(i);
                DictionaryTable.scrollTo(i);
            }
        }
    }

    public static void ShowError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}