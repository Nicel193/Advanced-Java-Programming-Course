package kukuiev.advjava.labthird.firsttask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class Program extends Application {
    public static Logger logger = LogManager.getLogger(Program.class);
    public static String PathToFiles = "./firsttaskfiles/Psychologist";

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("DoctorForm.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Прийом у лікаря");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Program.logger.info("Program started");

        launch(args);
    }
}
