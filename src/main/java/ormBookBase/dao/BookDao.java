package ormBookBase.dao;

import bookBase.Book;

import java.util.List;

public interface BookDao {
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByTitle(String author);
}
