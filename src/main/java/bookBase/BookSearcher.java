package bookBase;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@Transactional(readOnly = true)
public class BookSearcher {


    private static final Properties DB_SETTINGS = new Properties();
    private List<Book> result = new ArrayList<>();

    static {
        try {
            DB_SETTINGS.load(BookBaseAdapter.class.getResourceAsStream("/db/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchBooks(String categorySearch, String condition) {
        switch (categorySearch) {
            case "title" -> {
                return searchByTitle(condition);
            }
            case "author" -> {
                return searchByAuthor(condition);
            }
            case "price" -> {
                return searchByPrice(condition);
            }
        }
        return null;
    }

    @SneakyThrows
    private List<Book> searchByTitle(String condition) {
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
            return collectResults(resultSet);
        }
    }

    @SneakyThrows
    private List<Book> searchByAuthor(String condition) {
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
            return collectResults(resultSet);
        }
    }

    @SneakyThrows
    public List<Book> searchByPrice(String price) {
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
            return collectResults(resultSet);
        }
    }

    private List<Book> collectResults(ResultSet resultSet) throws SQLException {
        List<Book> result = new ArrayList<>();
        while(resultSet.next()) {
            result.add(new Book(
                    resultSet.getString("isbn"),
                    resultSet.getString("title"),
                    resultSet.getString("url"),
                    resultSet.getInt("pages"),
                    resultSet.getString("name"),
                    resultSet.getBigDecimal("price")));
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("db.login"),
                DB_SETTINGS.getProperty("db.password"));
    }

    private void writeResponse() {
        if(!result.isEmpty()) {
            for(Book book : result) {
                System.out.println(book);
            }
        } else {
            System.out.println("Ничего не найдено");
        }
    }
}
