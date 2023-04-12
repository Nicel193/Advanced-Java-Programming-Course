package kukuiev.advjava.labsecond.secondtask;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * @author Kukuiev Ruslan KN-221A
 */
public class FileSearcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Створення об'єкту Scanner
        System.out.print("Увести з клавіатури ім'я певної теки: ");
        String directoryPath = scanner.nextLine(); // Зчитування введеного рядка

        FindWithIO(directoryPath);
        FindWithNIO(directoryPath);
    }

    /**
     * Search using java.io.File
     * @param directoryPath
     */
    private static void FindWithIO(String directoryPath) {
        System.out.println("Пошук файлів та каталогів в " + directoryPath + " за допомогою java.io.File: ");
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Тека не існує!");
        } else {
            listFilesAndDirectoriesWithFile(directory);
        }
    }

    /**
     * Search using java.nio.file
     * @param directoryPath
     */
    private static void FindWithNIO(String directoryPath) {
        try {
            System.out.println("\nПошук файлів та каталогів в " + directoryPath + " за допомогою java.nio.file: ");
            Path directoryPathObj = Paths.get(directoryPath);
            if (!Files.exists(directoryPathObj) || !Files.isDirectory(directoryPathObj)) {
                System.out.println("Тека не існує!");
            } else {

                Files.walkFileTree(directoryPathObj, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        System.out.println(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });

            }
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private static void listFilesAndDirectoriesWithFile(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            System.out.println(file.getPath());
            if (file.isDirectory()) {
                listFilesAndDirectoriesWithFile(file);
            }
        }
    }
}
