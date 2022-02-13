package ormBookBase;

import ormBookBase.dto.AuthorDto;
import ormBookBase.dto.BookDto;
import utils.CsvReader;

import java.util.Set;

public class OrmBooksRunner {

    public static void main(String[] args) {
        Set<BookDto> books = CsvReader.readAsSet("/books/bookData.csv", BookDto.class, ';', true);
        for (BookDto book :books) {
            System.out.println(book);
        }
        Set<AuthorDto> authors = CsvReader.readAsSet("/books/bookData.csv", AuthorDto.class, ';', true);
        int id = 1;
        for (AuthorDto author : authors) {
            author.setId(id++);
            System.out.println(author);
        }

        new BookBaseOrmAdapter(books, authors).restartTables("books");
    }

}