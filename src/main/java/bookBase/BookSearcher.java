package bookBase;

import lombok.SneakyThrows;
import receiver.ValueReceiver;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class BookSearcher {

    private static final Properties DB_SETTINGS = new Properties();

    static {
        try {
            DB_SETTINGS.load(BookBaseAdapter.class.getResourceAsStream("/db/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBooks(String categorySearch, String condition) {
        switch (categorySearch) {
            case "title" -> searchByTitle(condition);
            case "author" -> searchByAuthor(condition);
            case "price" -> searchByPrice(condition);
        }
    }

    @SneakyThrows
    private void searchByTitle(String condition) {
        try (final Connection connection = getConnection();
             final PreparedStatement searchQuery = connection.prepareStatement(
                     """ 
                            select * from books
                            left join authors
                            on books.author_id = authors.id
                            where books.title like ?
                        """
             )) {
            searchQuery.setString(1, "%" + condition + "%");
            ResultSet resultSet = searchQuery.executeQuery();
            while(resultSet.next()) {
                  System.out.println(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("pages"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")));
            }
        }
    }

    @SneakyThrows
    private void searchByAuthor(String condition) {
        try (final Connection connection = getConnection();
             final PreparedStatement searchQuery = connection.prepareStatement(
                     """ 
                            select * from authors
                            left join books
                            on authors.id = books.author_id
                            where authors.name like ?
                        """
             )) {
            searchQuery.setString(1, "%" + condition + "%");
            ResultSet resultSet = searchQuery.executeQuery();
            while(resultSet.next()) {
                System.out.println(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("pages"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")));
            }
        }
    }

    @SneakyThrows
    public void searchByPrice(String price) {
        try (final Connection connection = getConnection();
             final PreparedStatement searchQuery = connection.prepareStatement(
                     """ 
                            select * from authors
                            left join books
                            on authors.id = books.author_id
                            where price <= ?
                        """
             )) {
            searchQuery.setString(1, price);
            ResultSet resultSet = searchQuery.executeQuery();
            while(resultSet.next()) {
                System.out.println(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("pages"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")));
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("db.login"),
                DB_SETTINGS.getProperty("db.password"));
    }
}
