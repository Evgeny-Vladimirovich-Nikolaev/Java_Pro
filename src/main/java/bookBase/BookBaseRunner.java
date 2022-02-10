package bookBase;

import utils.CsvReader;

import java.util.List;

public class BookBaseRunner {

    public static void main(String[] args) {
        List<Book> books = (List<Book>) CsvReader.readAsList("/books/bookData.csv", Book.class, ';', true);
        new BookBase().fillBase(books);
    }
}
