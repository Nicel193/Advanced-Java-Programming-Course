package kukuiev.advjava.labsecond.fourthtask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SortAndFilterLines {
    public static void main(String[] args) throws IOException {
        Path inputPath = Path.of("src/main/resources/input.txt");
        Path outputPath = Path.of("src/main/resources/output.txt");

        try {
            String output = Files.lines(inputPath)
                    .sorted(Comparator.comparingInt(String::length))
                    .filter(line -> line.contains("a"))
                    .collect(Collectors.joining(System.lineSeparator()));
            Files.writeString(outputPath, output);
        }catch (IOException e){
            System.err.println(e);
        }
    }
}