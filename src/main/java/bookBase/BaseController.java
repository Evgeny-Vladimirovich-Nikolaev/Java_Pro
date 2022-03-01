package bookBase;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.core.Local;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

@ShellComponent
public class BaseController {

    private Locale locale;
    private ResourceBundle resources = ResourceBundle.getBundle("messages");
    private String EXIT_PHRASE;// = resources.getString("app.exit");
    private String APP_ID;// = "appId";

//    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER = new ThreadLocal<>() {
//        @Override
//        public ObjectMapper get() {
//            return new ObjectMapper();
//        }
//    };

    BookSearcher bookSearcher = new BookSearcher();

    //@PostConstruct
    private void reloadResourceBundle () {
        ResourceBundle resources = ResourceBundle.getBundle("messages", locale);
        String EXIT_PHRASE = resources.getString("app.exit");
        String APP_ID = "appId";
//    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER = new ThreadLocal<>() {
//        @Override
//        public ObjectMapper get() {
//            return new ObjectMapper();
//        }
//    };

        //BookSearcher bookSearcher = new BookSearcher();
    }

    @ShellMethod(value = "Searching by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        System.out.println(EXIT_PHRASE);
        bookSearcher.searchBooks("title", title);
    }

    @ShellMethod(value = "Searching by title", key = {"author", "a"})
    public void createRequestByAuthor(@ShellOption({"-a, --author"}) String author) {
        Locale anotherLocal = new Locale("en");
        resources = ResourceBundle.getBundle("messages", anotherLocal);
        EXIT_PHRASE = resources.getString("app.exit");
        System.out.println(EXIT_PHRASE);
        bookSearcher.searchBooks("author", author);
    }


    @ShellMethod(value = "Searching by title", key = {"price", "p"})
    public void createRequestByPrice(@ShellOption({"-p, --price"}) String price) {
        Locale anotherLocal = new Locale("fr");
        resources = ResourceBundle.getBundle("messages", anotherLocal);
        EXIT_PHRASE = resources.getString("app.exit");
        System.out.println(EXIT_PHRASE);
        bookSearcher.searchBooks("price", price);
    }

}

