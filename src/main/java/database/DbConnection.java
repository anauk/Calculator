package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String db_name = "test";
    private String server = "localhost";
    private String path = String.format(
            "jdbc:postgresql://%s:5432/%s",
            server,
            db_name);
    private String username = "postgres";
    private String password = "secret";

    private Connection connection = null;

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(path, username, password);
    }

    public Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException("Something went wrong ", e);
            }
        }
        return this.connection;
    }

}
