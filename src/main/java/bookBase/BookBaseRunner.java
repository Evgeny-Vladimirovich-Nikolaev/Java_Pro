package bookBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import receiver.ValueReceiver;
import utils.CsvReader;

import java.util.Set;

@SpringBootApplication
public class  BookBaseRunner {

    public static void main(String[] args) {
        Set<Book> books = CsvReader.readAsSet("/books/bookData.csv", Book.class, ';', true);
        new BookBaseAdapter().fillBase(books);
        SpringApplication.run(BookBaseRunner.class, args);
    }

}
