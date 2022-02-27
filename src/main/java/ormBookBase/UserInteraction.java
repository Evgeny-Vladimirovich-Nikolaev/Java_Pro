package ormBookBase;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ormBookBase.dao.AuthorDao;
import ormBookBase.dao.BookDao;
import ormBookBase.dto.Book;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class UserInteraction {

    private static BookDao bookDao;

    @Autowired
    public UserInteraction(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ShellMethod(key = "hello-to", value = "Say hello to username")
    public String helloTo(@ShellOption({"username", "u"}) String username) {
        return username;
    }

    @ShellMethod(value = "Searching by title", key = {"title", "t"})
    public void createRequestByTitle(@ShellOption({"-t, --title"}) String title) {
        List<Book> books = bookDao.searchByTitle(title);
        for(Book book : books) {
            System.out.println(book);
        }
    }

}

