package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String path = "jdbc:postgresql://localhost:5432/fs7";
    private String name = "postgres";
    private String password = "secret";
    private Connection connection;


    private  Connection connect() throws SQLException {
        return DriverManager.getConnection(path, name, password);
    }
    public Connection connection() {
        if(connection == null){
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException("DB Connection error", e);
            }
        }
        return this.connection;
    }
}
