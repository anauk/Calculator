import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletCalculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        resp.getWriter().printf("%d %s %d = %d", a, operation, b, result);
    }
}
