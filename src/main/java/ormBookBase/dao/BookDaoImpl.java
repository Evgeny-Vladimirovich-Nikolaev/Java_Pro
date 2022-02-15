package ormBookBase.dao;

import bookBase.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;

import java.util.List;

public class BookDaoImpl implements BookDao {
    public void addBooks(List<Book> booksList) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            for (Book book : booksList) {
                session.save(book);
            }
            transaction.commit();
        }
    }

    public void addBook(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Author> searchAuthorQuery = session.createQuery("from Author where author =: name", Author.class);
            searchAuthorQuery.setParameter("name", author);
            Author queryAuthor = searchAuthorQuery.getSingleResult();
            Query<Book> searchBooksQuery = session.createQuery("from Book where authors.id =: author_id", Book.class);
            searchBooksQuery.setParameter("author_id", queryAuthor.getId());
            List<Book> searchedBooks = searchBooksQuery.getResultList();
            transaction.commit();
            return searchedBooks;
        }
    }
}
