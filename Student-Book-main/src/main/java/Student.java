import java.util.List;

public final class Student {
    private final String name;
    private final List<Book> books;

    public Student(String name, List<Book> books) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя студента не указано");
        }
        if (books == null || books.size() < 5) {
            throw new IllegalArgumentException(
                    "У каждого студента должно быть минимум 5 книг"
            );
        }

        this.name = name;
        this.books = List.copyOf(books);
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return String.format(
                "Студент: %s%nКниги: %s%n",
                name, books
        );
    }
}
