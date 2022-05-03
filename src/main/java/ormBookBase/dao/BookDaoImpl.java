package main.java.ormBookBase.dao;
import org.springframework.stereotype.Service;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ormBookBase.booksUtils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookDaoImpl implements BookDao {

    @Override
    public void addBooks(List<Book> booksList) {
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
    public List<Book> searchByTitle(String title)  {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Book WHERE title like :t";
            Query query = session.createQuery(hql);
            query.setParameter("t", "%" + title + "%");
            List<Book> books = query.list();
            transaction.commit();
            for (Book book : books) {
                book.setAuthorName(book.getAuthor().getName());
            }
            return books;
        }
    }

    @Override
    public List<Book> searchByAuthor(String name) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            List<Book>  books = new ArrayList<>();
            Transaction transaction = session.beginTransaction();
            Query<Author> authorQuery = session.createQuery("FROM Author WHERE name LIKE :n", Author.class);
            authorQuery.setParameter("n", "%" + name + "%");
            List<Author> authors = authorQuery.list();
            for(Author author : authors) {
                Query<Book> bookQuery = session.createQuery("FROM Book WHERE author_id = :id", Book.class);
                bookQuery.setParameter("id", author.getId());
                List<Book> temp = bookQuery.list();
                books.addAll(temp);
            }
            transaction.commit();
            for (Book book : books) {
                book.setAuthorName(book.getAuthor().getName());
            }
            return books;
        }
    }

    @Override
    public List<Book> searchByPrice(int price) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Book WHERE price <= :p";
            Query query = session.createQuery(hql);
            query.setParameter("p", price);
            List<Book> books = query.list();
            transaction.commit();
            for (Book book : books) {
                book.setAuthorName(book.getAuthor().getName());
            }
            return books;
        }
    }

}
