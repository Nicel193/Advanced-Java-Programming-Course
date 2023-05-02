package fourthtask;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

public class DictionaryController extends Application {

    private Map<String, String> dictionary = new HashMap<>();

    // Остальной код
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = (Pane)FXMLLoader.load(getClass().getResource("/DictionaryD.fxml"));
        Scene scene = new Scene(root, 580, 500);
        stage.setScene(scene);
        stage.setTitle("Переписи населення");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private TableView<Map.Entry<String, String>> DictionaryTable;

    private ObservableList<Map.Entry<String, String>> tableData;

    @FXML
    private  TableColumn<String, String> uaColumn;

    @FXML
    private  TableColumn<String, String> enColumn;

    @FXML
    public TextField ua;

    @FXML
    public TextField en;

    @FXML
    public void initialize() {
        // Set up the table columns
        TableColumn<Map.Entry<String, String>, String> uaCol = new TableColumn<>("Українська");
        uaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, String>, String> enCol = new TableColumn<>("English");
        enCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        DictionaryTable.getColumns().addAll(uaCol, enCol);

        // Set up the table data
        tableData = FXCollections.observableArrayList(dictionary.entrySet());
        DictionaryTable.setItems(tableData);
    }

    public void AddWords(javafx.event.ActionEvent actionEvent) {
        // Add the new word to the dictionary
        dictionary.put(ua.getText(), en.getText());

        // Update the table data
        tableData.setAll(dictionary.entrySet());
    }
}