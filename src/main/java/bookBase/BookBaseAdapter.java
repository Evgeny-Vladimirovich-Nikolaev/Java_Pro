package bookBase;

import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BookBaseAdapter {

    private static final Properties DB_SETTINGS = new Properties();

    static {
        try {
            DB_SETTINGS.load(BookBaseAdapter.class.getResourceAsStream("/db/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillBase(Set<Book> books) {
        dropBooksAndAuthorsIfExists();
        createBooksAndAuthors();
        for (Book book : books) {
            insertBook(insertAuthor(book).orElse(null), book);
        }
    }

    @SneakyThrows(SQLException.class)
    public void dropBooksAndAuthorsIfExists() {
        try (final Connection connection = getConnection();
             final PreparedStatement dropIfExistsTableBooks = connection.prepareStatement("drop table if exists books");
             final PreparedStatement dropIfExistsTableAuthors = connection.prepareStatement("drop table if exists authors")) {
            dropIfExistsTableBooks.execute();
            dropIfExistsTableAuthors.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public void createBooksAndAuthors() {
        try (final Connection connection = getConnection();
             final PreparedStatement createTableAuthors = connection.prepareStatement(
                     """
                             create table authors
                             (
                                 id int auto_increment,
                                 name varchar(255) not null unique,
                                 constraint authors_pk
                                     primary key (id)
                             );
                             """
             );
             final PreparedStatement createTableBooks = connection.prepareStatement(
                     """
                             create table books
                             (
                             	isbn bigint not null unique,
                             	title varchar(255) not null,
                             	url varchar(4000) null,
                             	price numeric null,
                             	pages int null,
                             	author_id int null,
                             	constraint books_pk
                             		primary key (isbn),
                             	constraint books_authors_id_fk
                             		foreign key (author_id) references authors (id)
                             );
                             """
             )) {
            createTableAuthors.execute();
            createTableBooks.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public Optional<Integer> insertAuthor(Book book) {
        final String authorName = book.getAuthor();
        if (StringUtils.isBlank(authorName)) {
            return Optional.empty();
        }

        try (final Connection connection = getConnection();
             final PreparedStatement authorStatement = connection.prepareStatement("insert into authors(name) values(?)");
             final PreparedStatement searchAuthorStatement = connection.prepareStatement("select id from authors where name = ?")
        ) {
            authorStatement.setString(1, authorName);
            authorStatement.execute();
            searchAuthorStatement.setString(1, authorName);
            final ResultSet resultSet = searchAuthorStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(resultSet.getInt("id"));
            }
        }
        return Optional.empty();
    }

    @SneakyThrows(SQLException.class)
    public void insertBook(Integer authorId, Book book) {
        try (final Connection connection = getConnection();
             final PreparedStatement fullBookStatement = connection.prepareStatement(
                     """
                             insert into books(isbn, title, url, price, pages, author_id)
                             values (?, ?, ?, ?, ?, ?)
                             """
             );
             final PreparedStatement bookStatementWithoutAuthor = connection.prepareStatement(
                     """
                             insert into books(isbn, title, url, price, pages)
                             values (?, ?, ?, ?, ?)
                             """
             )
        ) {
            if (authorId != null) {
                fullBookStatement.setLong(1, convertIsbn(book));
                fullBookStatement.setString(2, book.getTitle());
                fullBookStatement.setString(3, book.getUrl());
                fullBookStatement.setBigDecimal(4, book.getPrice());
                fullBookStatement.setInt(5, book.getPageCount());
                fullBookStatement.setInt(6, authorId);
                fullBookStatement.execute();
            } else {
                bookStatementWithoutAuthor.setLong(1, convertIsbn(book));
                bookStatementWithoutAuthor.setString(2, book.getTitle());
                bookStatementWithoutAuthor.setString(3, book.getUrl());
                bookStatementWithoutAuthor.setBigDecimal(4, book.getPrice());
                bookStatementWithoutAuthor.setInt(5, book.getPageCount());
                bookStatementWithoutAuthor.execute();
            }
        }
    }

    private long convertIsbn(Book book) {
        return Long.parseLong(book.getIsbn().replaceAll("[^0-9.]", ""));
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("db.login"),
                DB_SETTINGS.getProperty("db.password"));
    }
}
