import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Student> students;
        try (InputStream inputStream = Main.class.getResourceAsStream("/students.txt")) {
            if (inputStream == null) {
                throw new IOException("Файл students.txt не найден в classpath (src/main/resources)");
            }
            students = StudentFileReader.read(inputStream);
        }

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .sorted()
                .limit(3)
                .map(Book::getYear)
                .findAny()
                .ifPresentOrElse(
                        year -> System.out.println(
                                "Год выпуска найденной книги: " + year
                        ),
                        () -> System.out.println(
                                "Книга, выпущенная после 2000 года, отсутствует"
                        )
                );
    }
}
