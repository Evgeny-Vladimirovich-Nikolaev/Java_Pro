package ormBookBase;

import ormBookBase.csvModels.BookModel;
import ormBookBase.dao.AuthorDao;
import ormBookBase.dao.AuthorDaoImpl;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import utils.CsvReader;

import java.util.*;

public class OrmBooksRunner {

    private static final AuthorDao authorDao = new AuthorDaoImpl();
    private static Map<String, Author> authorsMap = new HashMap<>();

    public static void main(String[] args) {
        Set<Author> authors = CsvReader.readAsSet("/books/bookData.csv", Author.class, ';', true);
        int id = 1;
        for (Author author : authors) {
            author.setId(id++);
            authorsMap.put(author.getName(), author);
        }

        Set<BookModel> models = CsvReader.readAsSet("/books/bookData.csv", BookModel.class, ';', true);
        List<Book> books = new ArrayList<>();
        for (BookModel model : models) {
            Book book = model.getBook();
            Author author = authorsMap.get(model.getAuthor());
            book.setAuthor(author);
            book.setAuthor_id(author.getId());
            book.setAuthor(authorsMap.get(model.getAuthor()));
            books.add(book);
        }

        for (Book b : books) {
            System.out.println(b);
        }

        for(Author author : authors) {
            System.out.println(author);
        }

        authorDao.addAuthors(authors.stream().toList());
    }

}