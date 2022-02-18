package anotherBookBase;

import anotherBookBase.AnotherBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ormBookBase.booksUtils.HibernateUtil;

import java.util.List;

public class AnotherBookDao {

    public void addBooks(List<AnotherBook> bookList) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            for (AnotherBook book : bookList) {
                session.save(book);
            }
            transaction.commit();
        }
    }

}
