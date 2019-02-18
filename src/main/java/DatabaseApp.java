import database.DBConnection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseApp {
    public static void main(String[] args) throws Exception {
        Connection connection = new DBConnection().connection();
        String sql = "INSERT INTO history(a,op,b,result) values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1,23);
        stm.setString(2,"-");
        stm.setInt(3,11);
        stm.setInt(4, 12);
        stm.execute();

       /* ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new ServletCalculator()), "/calc/*");
        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();*/
    }
}
