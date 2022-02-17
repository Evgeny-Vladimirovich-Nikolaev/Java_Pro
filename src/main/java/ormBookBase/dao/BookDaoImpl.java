package ormBookBase.dao;

import bookBase.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ormBookBase.booksUtils.HibernateUtil;


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

}
