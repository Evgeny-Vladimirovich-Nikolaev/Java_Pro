package ormBookBase.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void addAuthors(List<Author> authorsList) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            //deleteAuthors();
            for (Author author : authorsList) {
                session.save(author);
            }
            transaction.commit();
        }
    }


        public Author findAuthorById(long id) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                Query<Author> searchQuery = session.createQuery("from Author where id =: object", Author.class);
                searchQuery.setParameter("object", id);
                List<Author> authors = searchQuery.list();
                if (searchQuery.list().isEmpty()) {
                    throw new NoSuchElementException("В базе данных объект Authors с id = " + id + " не найдено");
                }
                transaction.commit();
                return authors.get(0);
            }
        }

        public void addAuthor(Author author) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                deleteAuthors();
                session.save(author);
                transaction.commit();
            }
        }

        public void deleteAuthors() {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                Query<Author> allQuery = session.createQuery("from Authors", Author.class);
                List<Author> authors = allQuery.list();
                if (!authors.isEmpty()) {
                    for (Iterator<Author> iterator = authors.iterator(); iterator.hasNext(); ) {
                        Author authors1 = iterator.next();
                        session.delete(authors1);
                    }
                }
                transaction.commit();
            }
        }
    }
