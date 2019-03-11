import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ServletCalculator extends HttpServlet {
    private final UserStorage security;
    private final Connection conn;

    public ServletCalculator(UserStorage security, Connection conn) {
        this.security = security;
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        boolean checked = security.check("user", "passwd");
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int a = pfr.getInt("a");
        int b = pfr.getInt("b");
        String command = pfr.getStr("op");
        int result = 0;
        String operation="";
        switch(command) {
            case "add":
                result = a + b;
                operation = "+";
                break;
            case "sub":
                result = a - b;
                operation = "-";
                break;
            case "mul":
                result = a * b;
                operation = "*";
                break;
            case "div":
                result = a / b;
                operation = "/";
                break;
        }
        saveOperationToDb(a,command,b,result);
        resp.getWriter().printf("%d %s %d = %d", a, operation, b, result);
    }


    private void saveOperationToDb(int a, String operation, int b, int result) {
        try {
            String sql = "INSERT INTO history1 (a, op, b, r) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, a);
            stm.setString(2, operation);
            stm.setInt(3, b);
            stm.setInt(4, result);
            stm.execute();
        } catch (SQLException e) {
            new IllegalStateException("Smth went wrong", e);

        }
    }
}
