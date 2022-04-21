package main.java.bookBase;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Transactional(readOnly = true)
public class BookSearcher {

    @Autowired
    private BaseAdapter baseAdapter;
    private List<Book> result = new ArrayList<>();

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
        return Collections.emptyList();
    }

    @SneakyThrows
    private List<Book> searchByTitle(String condition) {
        try (final Connection connection = baseAdapter.getConnection();
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
        try (final Connection connection = baseAdapter.getConnection();
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
        try (final Connection connection = baseAdapter.getConnection();
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
