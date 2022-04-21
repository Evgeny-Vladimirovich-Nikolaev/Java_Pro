package main.java.bookBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@org.springframework.stereotype.Repository
public class BaseAdapter {

    private final Properties DB_SETTINGS = new Properties();

    {
        try {
            DB_SETTINGS.load(Repository.class.getResourceAsStream("/db/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("db.login"),
                DB_SETTINGS.getProperty("db.password"));
    }

}
