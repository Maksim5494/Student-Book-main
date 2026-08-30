import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class StudentFileReader {

    private StudentFileReader() {
    }

    public static List<Student> read(InputStream inputStream) throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }

                try {
                    students.add(parseStudent(line));
                } catch (RuntimeException exception) {
                    throw new IllegalArgumentException(
                            "Ошибка в строке " + lineNumber
                                    + ": " + exception.getMessage(),
                            exception
                    );
                }
            }
        }

        return List.copyOf(students);
    }

    private static Student parseStudent(String line) {
        String[] studentParts = line.split("\\|", 2);

        if (studentParts.length != 2) {
            throw new IllegalArgumentException(
                    "Ожидается формат: студент|книга#книга"
            );
        }

        String name = studentParts[0].trim();
        String[] bookParts = studentParts[1].split("#");
        List<Book> books = new ArrayList<>();

        for (String bookText : bookParts) {
            books.add(parseBook(bookText));
        }

        return new Student(name, books);
    }

    private static Book parseBook(String text) {
        String[] fields = text.split(";", -1);

        if (fields.length != 4) {
            throw new IllegalArgumentException(
                    "Книга должна иметь формат: название;автор;год;страницы"
            );
        }

        return new Book(
                fields[0].trim(),
                fields[1].trim(),
                Integer.parseInt(fields[2].trim()),
                Integer.parseInt(fields[3].trim())
        );
    }
}
