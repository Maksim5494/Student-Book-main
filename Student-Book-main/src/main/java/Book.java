import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final int year;
    private final int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public int getYear() { return year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && 
               pages == book.pages && 
               Objects.equals(title, book.title) && 
               Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, pages);
    }

    @Override
    public int compareTo(Book other) {
        // Сравнение по всем полям для согласованности с equals
        int res = this.title.compareTo(other.title);
        if (res != 0) return res;
        res = this.author.compareTo(other.author);
        if (res != 0) return res;
        res = Integer.compare(this.year, other.year);
        if (res != 0) return res;
        return Integer.compare(this.pages, other.pages);
    }

    @Override
    public String toString() {
        return String.format("Книга '%s' (%s, %d г., %d стр.)", title, author, year, pages);
    }
}
