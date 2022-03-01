package ormBookBase;

import bookBase.BookBaseRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import ormBookBase.controller.Controller;
import ormBookBase.controller.ControllerImpl;
import ormBookBase.csvModels.BookModel;
import ormBookBase.dao.AuthorDao;
import ormBookBase.dao.AuthorDaoImpl;
import ormBookBase.dao.BookDao;
import ormBookBase.dao.BookDaoImpl;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import receiver.ValueReceiver;
import utils.CsvReader;

import javax.persistence.PersistenceException;
import java.util.*;

@Configuration
public class OrmBooksRunner {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext("ormBookBase");
    private static final AuthorDao authorDao = appContext.getBean(AuthorDao.class);
    private static final BookDao bookDao = appContext.getBean(BookDao.class);

    public static void main(String[] args) {
        saveAuthors();
        saveBooks();
        Controller controller = appContext.getBean(ControllerImpl.class, bookDao);
        controller.startSearching();
        ValueReceiver.closeReader();
    }

    private static void saveAuthors() {
        Set<Author> authors = CsvReader.readAsSet("/books/bookData.csv", Author.class, ';', true);
        authorDao.addAuthors(authors.stream().toList());
    }

    private static void saveBooks() {
        Set<BookModel> models = CsvReader.readAsSet("/books/bookData.csv", BookModel.class, ';', true);
        List<Book> books = new ArrayList<>();
        for (BookModel model : models) {
            Book book = model.getBook();
            try {
                Author author = authorDao.findAuthorByBName(book.getAuthorName());
                book.setAuthor(author);
                book.setAuthor_id(author.getId());
                book.setAuthorName(author.getName());
                System.out.println(book);
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            books.add(book);
        }
        bookDao.addBooks(books);
    }
}