package bookBase;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.core.Local;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@ShellComponent
public class BaseController {

    private Locale locale;
    private ResourceBundle resources = ResourceBundle.getBundle("messages");
    private String APP_ID = "appId";

    BookSearcher bookSearcher = new BookSearcher();

    private void reloadResourceBundle () {
        ResourceBundle resources = ResourceBundle.getBundle("messages", locale);
    }

    @ShellMethod(value = "change language", key = {"lang", "l"})
    public void changeLocale(@ShellOption({"-l, --lang"}) String lang) {
        resources = ResourceBundle.getBundle("messages", new Locale(lang));
    }

    @ShellMethod(value = "search by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        List<Book> books = bookSearcher.searchBooks("title", title);
        if(books == null) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("results.by.title"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }

    @ShellMethod(value = "search by author", key = {"author", "a"})
    public void createRequestByAuthor(@ShellOption({"-a, --author"}) String author) {
        List<Book> books = bookSearcher.searchBooks("author", author);
        if(books == null) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("results.by.author"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }


    @ShellMethod(value = "search by price", key = {"price", "p"})
    public void createRequestByPrice(@ShellOption({"-p, --price"}) String price) {
        List<Book> books = bookSearcher.searchBooks("price", price);
        if(books == null) {
            System.out.println(resources.getString("no.results"));
        } else {
            System.out.println(resources.getString("results.by.price"));
            for(Book book : books) {
                System.out.println(book);
            }
        }
    }

}

