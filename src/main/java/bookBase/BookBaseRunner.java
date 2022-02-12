package bookBase;

import receiver.ValueReceiver;
import utils.CsvReader;

import java.util.Set;

public class BookBaseRunner {

    public static void main(String[] args) throws Exception {
        Set<Book> books = CsvReader.readAsSet("/books/bookData.csv", Book.class, ';', true);
        new BookBaseAdapter().fillBase(books);
        new RequestRunner().startSearching();
        ValueReceiver.closeReader();
    }

}
