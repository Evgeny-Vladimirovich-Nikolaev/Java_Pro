package ormBookBase.dao;

import bookBase.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;

import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public List<Book> findBooksByAuthor(String author) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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

    @Override
    public List<Book> findBooksByTitle(String title) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Book> searchBooksQuery = session.createQuery("from Book where title =: title", Book.class);
        searchBooksQuery.setParameter("title", title);
        List<Book> searchedBooks = searchBooksQuery.getResultList();
        transaction.commit();
        return searchedBooks;
    }

}
