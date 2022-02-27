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

    @ShellMethod(key = "hello-to", value = "Say hello to username")
    public String helloTo(@ShellOption({"username", "u"}) String username) {
        return username;
    }

    @ShellMethod(value = "Searching by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        bookSearcher.searchBooks("title", title);
    }

}

