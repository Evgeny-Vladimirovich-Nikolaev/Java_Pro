package ormBookBase.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ormBookBase.booksUtils.HibernateUtil;
import ormBookBase.dto.Author;


import javax.persistence.NoResultException;
import java.util.List;

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
            Author author = query.getSingleResult();
            transaction.commit();
            return author;
        }
    }

    @Override
    public Author findById(int id) throws NoResultException {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Query<Author> fromAuthors = session.createQuery("FROM Author WHERE id = :id");
            fromAuthors.setParameter("id", id);
            return fromAuthors.getSingleResult();
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
