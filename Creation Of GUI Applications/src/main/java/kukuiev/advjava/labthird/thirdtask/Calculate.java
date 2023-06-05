package kukuiev.advjava.labthird.thirdtask;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigDecimal;

/**
 * @author Kukuiev Ruslan KN-221A
 **/
public class Calculate extends Application {

    private TextField num1Field;
    private TextField num2Field;
    private TextField resultField;
    private RadioButton addRadio;
    private RadioButton subRadio;
    private RadioButton mulRadio;
    private RadioButton divRadio;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Створюємо елементи управління
        Text title = new Text("Калькулятор");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text num1Label = new Text("Число 1:");
        num1Field = new TextField();

        Text num2Label = new Text("Число 2:");
        num2Field = new TextField();

        HBox operationBox = new HBox();
        operationBox.setSpacing(10);
        ToggleGroup operationGroup = new ToggleGroup();
        addRadio = new RadioButton("Додати");
        addRadio.setToggleGroup(operationGroup);
        subRadio = new RadioButton("Відняти");
        subRadio.setToggleGroup(operationGroup);
        mulRadio = new RadioButton("Помножити");
        mulRadio.setToggleGroup(operationGroup);
        divRadio = new RadioButton("Розділити");
        divRadio.setToggleGroup(operationGroup);
        operationBox.getChildren().addAll(addRadio, subRadio, mulRadio, divRadio);

        Button calculateButton = new Button("Розрахувати");
        calculateButton.setOnAction(event -> calculate());

        Text resultLabel = new Text("Результат:");
        resultField = new TextField();
        resultField.setEditable(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(title, 0, 0, 2, 1);
        grid.add(num1Label, 0, 1);
        grid.add(num1Field, 1, 1);
        grid.add(num2Label, 0, 2);
        grid.add(num2Field, 1, 2);
        grid.add(operationBox, 0, 3, 2, 1);
        grid.add(calculateButton, 0, 4);
        grid.add(resultLabel, 0, 5);
        grid.add(resultField, 1, 5);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(title, grid);

        Scene scene = new Scene(vbox, 370, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Калькулятор");
        primaryStage.show();
    }

    /**
     * Calculate values
     **/
    private void calculate() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            if (addRadio.isSelected()) {
                result = num1 + num2;
            } else if (subRadio.isSelected()) {
                result = num1 - num2;
            } else if (mulRadio.isSelected()) {
                result = num1 * num2;
            } else if (divRadio.isSelected()) {
                if (num2 == 0) {
                    ShowError("Не можна ділити на 0");
                    return;
                }
                result = num1 / num2;
            }

            resultField.setText(BigDecimal.valueOf(result).toString());
        } catch (NumberFormatException e) {
            ShowError("Невірний ввід");
        }
    }

    public static void ShowError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}