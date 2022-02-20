package ormBookBase;

import ormBookBase.csvModels.BookModel;
import ormBookBase.dao.AuthorDao;
import ormBookBase.dao.AuthorDaoImpl;
import ormBookBase.dao.BookDao;
import ormBookBase.dao.BookDaoImpl;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import receiver.ValueReceiver;
import utils.CsvReader;

import java.util.*;

public class OrmBooksRunner {

    private static final AuthorDao authorDao = new AuthorDaoImpl();
    private static final BookDao bookDao = new BookDaoImpl();
    private static Map<String, Author> authorsMap = new HashMap<>();

    public static void main(String[] args) {
        saveAuthors();
        saveBooks();
        new Controller().startSearching();
        ValueReceiver.closeReader();
    }

    private static void saveAuthors() {
        Set<Author> authors = CsvReader.readAsSet("/books/bookData.csv", Author.class, ';', true);
        for (Author author : authors) {
            authorsMap.put(author.getName(), author);
        }
        authorDao.addAuthors(authors.stream().toList());
    }

    private static void saveBooks() {
        Set<BookModel> models = CsvReader.readAsSet("/books/bookData.csv", BookModel.class, ';', true);
        List<Book> books = new ArrayList<>();
        for (BookModel model : models) {
            Book book = model.getBook();
            Author author = authorDao.findAuthorByBName(book.getAuthorName());
            book.setAuthor(author);
            book.setAuthor_id(author.getId());
            book.setAuthorName(author.getName());
            books.add(book);
        }
        bookDao.addBooks(books);
    }
}