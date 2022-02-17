package ormBookBase.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;

import java.util.Iterator;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void addAuthors(List<Author> authorsList) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            deleteAuthors();
            for (Author author : authorsList) {
                session.save(author);
            }
            transaction.commit();
        }
    }

    public void deleteAuthors() {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                Query<Author> allQuery = session.createQuery("from Author", Author.class);
                List<Author> authors = allQuery.list();
                if (!authors.isEmpty()) {
                    for (Iterator<Author> iterator = authors.iterator(); iterator.hasNext(); ) {
                        Author author = iterator.next();
                        session.delete(author);
                    }
                }
                transaction.commit();
            }
        }
    }
