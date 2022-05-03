package main.java.ormBookBase.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;


import javax.persistence.NoResultException;
import java.util.List;

@Service
public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void addAuthors(List<Author> authors) {
        deleteAuthors();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            for (Author author : authors) {
                session.save(author);
            }
            transaction.commit();
        } catch (Exception e) {
            addInSeparateTransactions(authors);
        }
    }

    private void addInSeparateTransactions(List<Author> authors) {
        for(Author author : authors) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(author);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("Нельзя добавить в базу объект:");
                System.out.println(author);
            }
        }
    }

    @Override
    public Author findAuthorByBName (String name)  throws NoResultException {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Author WHERE name like :n";
            Query<Author> query = session.createQuery(hql, Author.class);
            query.setParameter("n", "%" + name + "%");
            List<Author> authors = query.getResultList();
            transaction.commit();
            return authors.get(0);
        }
    }

    @Override
    public Author findById(int id) throws NoResultException {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Author> fromAuthors = session.createQuery("FROM Author WHERE id = :id");
            fromAuthors.setParameter("id", id);
            List<Author> authors = fromAuthors.getResultList();
            transaction.commit();
            return authors.get(0);
        }
    }

    private void deleteAuthors() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Author");
            transaction.commit();
        }
    }
}
