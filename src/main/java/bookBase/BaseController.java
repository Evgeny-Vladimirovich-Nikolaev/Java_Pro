package bookBase;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ormBookBase.dao.BookDao;
import ormBookBase.dto.Book;

import java.util.List;

@ShellComponent
public class BaseController {

    BookSearcher bookSearcher = new BookSearcher();

    @ShellMethod(value = "Searching by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        bookSearcher.searchBooks("title", title);
    }

    @ShellMethod(value = "Searching by title", key = {"author", "a"})
    public void createRequestByAuthor(@ShellOption({"-a, --author"}) String author) {
        bookSearcher.searchBooks("author", author);
    }


    @ShellMethod(value = "Searching by title", key = {"price", "p"})
    public void createRequestByPrice(@ShellOption({"-p, --price"}) String price) {
        bookSearcher.searchBooks("price", price);
    }

}

