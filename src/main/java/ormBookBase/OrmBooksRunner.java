package ormBookBase;

import ormBookBase.dto.Authors;
import ormBookBase.dto.Books;
import utils.CsvReader;

import java.util.Set;

public class OrmBooksRunner {

    public static void main(String[] args) {
        Set<Books> books = CsvReader.readAsSet("/books/bookData.csv", Books.class, ';', true);
        for (Books book :books) {
            System.out.println(book);
        }
        Set<Authors> authors = CsvReader.readAsSet("/books/bookData.csv", Authors.class, ';', true);
        int id = 1;
        for (Authors author : authors) {
            author.setId(id++);
            System.out.println(author);
        }

        new BookBaseOrmAdapter(books, authors).restartTables("books");
    }

}