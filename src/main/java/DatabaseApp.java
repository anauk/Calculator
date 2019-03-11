
import database.DbConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseApp {
    public static void main1(String[] args) throws SQLException {
        Connection conn = new DbConnection().connection();
        //String sql = "INSERT INTO authors (\"firstName\")  VALUES (?)";
        String sql = "INSERT INTO history (a, op, b, r) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, 11);
        stm.setString(2, "+");
        stm.setInt(3, 22);
        stm.setInt(4, 33);
        stm.execute();
    }
        public static void main(String[] args) throws SQLException {
            Connection conn = new DbConnection().connection();
            String createSQL = "CREATE TABLE IF NOT EXISTS public.users (id SERIAL PRIMARY KEY, name VARCHAR(32))";
            PreparedStatement stm = conn.prepareStatement(createSQL);
            stm.execute();
        }
}
