package kukuiev.advjava.labfourth.secondTask;

import java.lang.reflect.Field;

public class ConsoleApplication {
    public static void main(String[] args) {
        // Запитуємо у користувача ім'я класу
        System.out.println("Введіть ім'я класу:");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String className = "kukuiev.advjava.labfourth.secondTask.";
        className += scanner.nextLine();

        try {
            // Завантажуємо клас за введеним ім'ям
            Class<?> clazz = Class.forName(className);

            // Отримуємо всі поля класу, включаючи закриті і захищені
            Field[] fields = clazz.getDeclaredFields();

            // Виводимо інформацію про поля класу
            for (Field field : fields) {
                System.out.println("Назва поля: " + field.getName());
                System.out.println("Тип поля: " + field.getType().getSimpleName());
                System.out.println("Модифікатор доступу: " + java.lang.reflect.Modifier.toString(field.getModifiers()));
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Клас з таким ім'ям не знайдено.");
        }
    }
}
