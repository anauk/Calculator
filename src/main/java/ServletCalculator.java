import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        String command = req.getParameter("op");
        int result=0;
        String operation="";
        switch(command){
            case "sum": result = a+b;
            operation = "+";
            break;
            case "sub": result = a-b;
                operation = "-";
            break;
            case "mult": result = a*b;
                operation = "*";
            break;
            case "div": result = a/b;
                operation = "/";
            break;
        }
        PrintWriter writer = resp.getWriter();
        writer.printf("%d %s %d = %d", a, operation, b, result);
    }
}
