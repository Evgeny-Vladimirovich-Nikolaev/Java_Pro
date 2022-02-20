package ormBookBase.dao;

import ormBookBase.dto.Book;

import java.util.List;

public interface BookDao {
   void addBooks(List<Book> booksList);
   List<Book> searchByTitle(String title);
   List<Book> searchByAuthor(String author);
   List<Book> searchByPrice(int price);
}
