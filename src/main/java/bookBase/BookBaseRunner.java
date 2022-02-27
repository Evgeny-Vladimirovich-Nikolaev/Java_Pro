package bookBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import receiver.ValueReceiver;
import utils.CsvReader;

import java.util.Set;

@SpringBootApplication
public class  BookBaseRunner {

    public static void main(String[] args) throws Exception {
        Set<Book> books = CsvReader.readAsSet("/books/bookData.csv", Book.class, ';', true);
        new BookBaseAdapter().fillBase(books);
//        new RequestRunner().startSearching();
//        ValueReceiver.closeReader();

        SpringApplication.run(BookBaseRunner.class, args);
    }

}
