package ormBookBase.dao;

import bookBase.Book;

import java.util.List;

public interface BookDao {
   void addBooks(List<Book> booksList);
    void addBook(Book book);
    List<Book> findBooksByAuthor(String author);
}