package kukuiev.advjava.labthird.fourthtask;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

public class DictionaryController extends Application {

    @FXML
    private TableView<Map.Entry<String, String>> DictionaryTable;

    @FXML
    private  TableColumn<String, String> uaColumn;

    @FXML
    private  TableColumn<String, String> enColumn;

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
        // Set up the table columns
        TableColumn<Map.Entry<String, String>, String> uaCol = new TableColumn<>("Українська");
        uaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, String>, String> enCol = new TableColumn<>("English");
        enCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        DictionaryTable.getColumns().addAll(uaCol, enCol);

        // Set up the table data
        DictionaryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableData = FXCollections.observableArrayList(dictionary.entrySet());
        DictionaryTable.setItems(tableData);
    }

    // Остальной код
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane)FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Переписи населення");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void AddWords(javafx.event.ActionEvent actionEvent) {
        DictionaryTable.getSelectionModel().clearSelection();

        // Add the new word to the dictionary
        dictionary.put(ua.getText(), en.getText());

        // Update the table data
        tableData.setAll(dictionary.entrySet());
    }

    public void SearchWord(ActionEvent actionEvent) {
        DictionaryTable.getSelectionModel().clearSelection();
        
        String keyword = findWordField.getText();

        // Очистите предыдущий результат поиска
        DictionaryTable.getSelectionModel().clearSelection();

        // Ищем элементы, содержащие ключевое слово
        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).getKey().equals(keyword) || tableData.get(i).getValue().equals(keyword)) {
                // Если элемент содержит ключевое слово, выделяем его в таблице
                DictionaryTable.getSelectionModel().select(i);
                DictionaryTable.scrollTo(i);
            }
        }
    }
}