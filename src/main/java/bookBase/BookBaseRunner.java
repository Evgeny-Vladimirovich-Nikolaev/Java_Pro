package main.java.bookBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.CsvReader;

import java.util.Set;

@SpringBootApplication
public class  BookBaseRunner {

    public static void main(String[] args) {
        Set<Book> books = CsvReader.readAsSet("/books/bookData.csv", Book.class, ';', true);
        Repository repository = new Repository(new BaseAdapter());
        repository.dropBooksAndAuthorsIfExists();
        repository.createBooksAndAuthors();
        repository.fillBookBase(books);
        SpringApplication.run(BookBaseRunner.class, args);
    }

}
