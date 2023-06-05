package kukuiev.advjava.labfourth.fourthTask;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationsFinder {
    public static void main(String[] args) {
        // Запитуємо у користувача ім'я класу та методу
        System.out.println("Введіть ім'я класу:");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String className = "kukuiev.advjava.labfourth.fourthTask.";
        className += scanner.nextLine();

        System.out.println("Введіть ім'я методу:");
        String methodName = scanner.nextLine();

        try {
            // Завантажуємо клас за введеним ім'ям
            Class<?> clazz = Class.forName(className);

            // Отримуємо метод за введеним ім'ям
            Method method = clazz.getMethod(methodName, int.class, int.class);

            // Отримуємо всі анотації, якими позначений метод
            Annotation[] annotations = method.getDeclaredAnnotations();

            // Виводимо інформацію про анотації
            if (annotations.length > 0) {
                System.out.println("Анотації, якими позначений метод " + methodName + ":");
                for (Annotation annotation : annotations) {
                    System.out.println(annotation.annotationType().getSimpleName());
                }
            } else {
                System.out.println("Метод " + methodName + " не має анотацій.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Клас з таким ім'ям не знайдено.");
        } catch (NoSuchMethodException e) {
            System.out.println("Метод з таким ім'ям не знайдено.");
        }
    }
}
