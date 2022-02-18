package anotherBookBase;

import java.util.ArrayList;
import java.util.List;

public class Another {

    private static List<AnotherBook> books = new ArrayList<>();

    public static void main(String[] args) {
        fillBooks();
        saveBooks();
    }

    private static void fillBooks() {
        books.add(new AnotherBook(1, "Брюс Эккель", "Философия Java"));
        books.add(new AnotherBook(1, "Герберт Шилдт", "Java. Руководство для начинающих"));
        books.add(new AnotherBook(1, "Лафоре", "Алгоритмы и структуры данных на языке Java"));
    }

    private static void saveBooks() {
        AnotherBookDao anotherBookDao = new AnotherBookDao();
        anotherBookDao.addBooks(books);
    }

}
