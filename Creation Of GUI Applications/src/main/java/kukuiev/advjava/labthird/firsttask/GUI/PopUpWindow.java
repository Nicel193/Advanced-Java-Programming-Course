package kukuiev.advjava.labthird.firsttask.GUI;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class PopUpWindow {
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
}
