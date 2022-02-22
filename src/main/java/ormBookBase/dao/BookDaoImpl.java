package ormBookBase.dao;

import org.hibernate.query.Query;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ormBookBase.booksUtils.HibernateUtil;


import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Iterator;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBooks(List<Book> booksList) {
        deleteBooks();
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            for (Book book : booksList) {
                session.save(book);
            }
            transaction.commit();
        }catch (Exception e) {
            addInSeparateTransactions(booksList);
        }
    }

    private void addInSeparateTransactions(List<Book> booksList) {
        for(Book book : booksList) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(book);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("Нельзя добавить в базу объект:");
                System.out.println(book.getTitle());
            }
        }
    }

    @Override
    public List<Book> searchByTitle(String title)  throws NoResultException {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Book WHERE title like :t";
            Query query = session.createQuery(hql);
            query.setParameter("t", "%" + title + "%");
            List<Book> books = query.getResultList();
            if (!books.isEmpty()) {
                Query<Author> authorQuery = session.createQuery("FROM Author WHERE id = :id", Author.class);
                authorQuery.setParameter("id", books.get(0).getAuthor_id());
                Author author = authorQuery.getResultList().get(0);
                for(Book book : books) {
                    book.setAuthor(author);
                    System.out.println(author);
                    System.out.println(author.getName());
                    book.setAuthorName(author.getName());
                }
            }transaction.commit();
            return books;
        }
    }

    @Override
    public List<Book> searchByAuthor(String name)  throws NoResultException {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Author> authorQuery = session.createQuery("FROM Author WHERE name LIKE :n", Author.class);
            authorQuery.setParameter("n", "%" + name + "%");
            List<Author> authors = authorQuery.getResultList();
            Author author = authors.get(0);
            int id = author.getId();
            Query<Book> bookQuery = session.createQuery("FROM Book WHERE author_id = :id", Book.class);
            bookQuery.setParameter("id", id);
            List<Book> books = bookQuery.getResultList();
            transaction.commit();
            for (Book book : books) {
                book.setAuthor(author);
                book.setAuthor_id(id);
                book.setAuthorName(author.getName());
            }
            return books;
        }
    }

    @Override
    public List<Book> searchByPrice(int price)  throws NoResultException {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Book WHERE price <= :p";
            Query query = session.createQuery(hql);
            query.setParameter("p", price);
            List<Book> books = query.list();
            if (!books.isEmpty()) {
                Query<Author> authorQuery = session.createQuery("FROM Author WHERE id = :id", Author.class);
                authorQuery.setParameter("id", books.get(0).getAuthor_id());
                Author author = authorQuery.getSingleResult();
                for(Book book : books) {
                    book.setAuthor(author);
                    book.setAuthorName(author.getName());
                }
            }
            transaction.commit();
            return books;
        }
    }

    private void deleteBooks() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Book");
            transaction.commit();
        }
    }

}
