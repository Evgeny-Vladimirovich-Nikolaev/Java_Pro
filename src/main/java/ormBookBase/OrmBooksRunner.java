package ormBookBase;

import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import utils.CsvReader;

import java.util.Set;

public class OrmBooksRunner {

    public static void main(String[] args) {
        Set<Book> books = CsvReader.readAsSet("/books/bookData.csv", Book.class, ';', true);

        Set<Author> authors = CsvReader.readAsSet("/books/bookData.csv", Author.class, ';', true);
        int id = 1;
        for (Author author : authors) {
            author.setId(id++);
        }

        new BookBaseOrmAdapter(books, authors).restartTables("books");
    }

}