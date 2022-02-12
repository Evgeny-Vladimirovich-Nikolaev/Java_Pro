package ormBookBase;

import ormBookBase.dto.BookDto;
import utils.CsvReader;

import java.util.Set;

public class OrmBooksRunner {

    public static void main(String[] args) {
        Set<BookDto> books = CsvReader.readAsSet("/books/bookData.csv", BookDto.class, ';', true);
        for (BookDto book :books) {
            System.out.println(book);
        }
    }

}