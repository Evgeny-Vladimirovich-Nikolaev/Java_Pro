package bookBase;

import org.slf4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.*;

@ShellComponent
public class BaseController {

    private ResourceBundle resources = ResourceBundle.getBundle("messages");
    private final String APP_ID = "appId";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    BookSearcher bookSearcher = new BookSearcher();

    @ShellMethod(value = "change language", key = {"lang", "l"})
    public void changeLocale(@ShellOption({"-l, --lang"}) String lang) {
        resources = ResourceBundle.getBundle("messages", new Locale(lang));
    }

    @ShellMethod(value = "search by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        List<Book> books = bookSearcher.searchBooks("title", title);
        if(books == null || books.isEmpty()) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("books.results.by.title"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }

    @ShellMethod(value = "search by author", key = {"author", "a"})
    public void createRequestByAuthor(@ShellOption({"-a, --author"}) String author) {
        List<Book> books = bookSearcher.searchBooks("author", author);
        if(books == null || books.isEmpty()) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("books.results.by.author"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }


    @ShellMethod(value = "search by price", key = {"price", "p"})
    public void createRequestByPrice(@ShellOption({"-p, --price"}) String price) {
        List<Book> books = bookSearcher.searchBooks("price", price);
        if(books == null || books.isEmpty()) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("books.results.by.price"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }

}

