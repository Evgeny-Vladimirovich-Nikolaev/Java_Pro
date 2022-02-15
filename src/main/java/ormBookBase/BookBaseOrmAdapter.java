package ormBookBase;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;

import java.util.Set;

@RequiredArgsConstructor
public class BookBaseOrmAdapter {

    private static final Configuration CONFIG;
    private final Set<Book> books;
    private final Set<Author> authors;

    static {
        CONFIG = new Configuration();
        CONFIG.configure();
    }

    public void restartTables(String table) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        String hql = "from authors where id = 3";
        Query<Author> q = session.createQuery(hql, Author.class) ;
        q.executeUpdate() ;
        tran.commit();


//        String hql = "from books where author_id = 3";
//        Query<Book> q = session.createQuery(hql, Book.class) ;
//        q.executeUpdate() ;
//        tran.commit() ;
    }

    public static Session getSession() {
        // local SessionFactory bean created
        SessionFactory sessionFactory = CONFIG.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}
