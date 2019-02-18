import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServletHistory extends HttpServlet {
    private Connection con;

    public ServletHistory(Connection con) {
        this.con = con;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder result = new StringBuilder();
        try {
            String sql= "SELECT * FROM history";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){
                int a = resultSet.getInt("a");
                int b = resultSet.getInt("b");
                int res = resultSet.getInt("result");
                String op = resultSet.getString("op");
                result.append(a).append(op).append(b).append("=").append(res).append("\n");

            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        resp.getWriter().println(result);
    }


}
