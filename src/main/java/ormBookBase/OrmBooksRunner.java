package ormBookBase;

import ormBookBase.csvModels.BookModel;
import ormBookBase.dao.AuthorDao;
import ormBookBase.dao.AuthorDaoImpl;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrmBooksRunner {

    private static final AuthorDao authorDao = new AuthorDaoImpl();

    public static void main(String[] args) {
        Set<BookModel> models = CsvReader.readAsSet("/books/bookData.csv", BookModel.class, ';', true);
        List<Book> books = new ArrayList<>();
        for (BookModel model : models) {
            books.add(model.getBook());
        }

        for (Book b : books) {
            System.out.println(b);
        }

        Set<Author> authors = CsvReader.readAsSet("/books/bookData.csv", Author.class, ';', true);
        int id = 1;
        for (Author author : authors) {
            author.setId(id++);
            System.out.println(author);
        }

        authorDao.addAuthors(authors.stream().toList());

        //new BookBaseOrmAdapter(books, authors).restartTables("books");
    }

}