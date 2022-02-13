package ormBookBase;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ormBookBase.dto.Authors;
import ormBookBase.dto.Books;

import java.util.Set;

@RequiredArgsConstructor
public class BookBaseOrmAdapter {

    private static final Configuration CONFIG;
    private final Set<Books> books;
    private final Set<Authors> authors;

    static {
        CONFIG = new Configuration();
        CONFIG.configure();
    }

    public void restartTables(String table) {
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        String hql = "from books where author_id = 3";
        Query<Books> q = session.createQuery(hql, Books.class) ;
        q.executeUpdate() ;
        tran.commit() ;
    }

    public static Session getSession() {
        // local SessionFactory bean created
        SessionFactory sessionFactory = CONFIG.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}
