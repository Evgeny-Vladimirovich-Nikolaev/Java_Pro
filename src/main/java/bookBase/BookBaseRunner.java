package bookBase;

import utils.CsvReader;

import java.util.List;

public class BookBaseRunner {

    public static void main(String[] args) throws Exception {
        List<Book> books = (List<Book>) CsvReader.readAsList("/books/bookData.csv", Book.class, ';', true);
        new BookBaseAdapter().fillBase(books);
        new RequestRunner().startSearching();
        new BookSearcher().searchBooks("title", "Сов");
    }
}
