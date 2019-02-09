import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletCalculator extends HttpServlet {
    private int convert(String input, HttpServletRequest request){
        if(request.getParameter(input) == null){
            throw new IllegalStateException(String.format("Parametr %s missing", input));
        }
        return Integer.parseInt(request.getParameter("a"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseNessege = null;
        try {
            int a = convert("a",req);
            int b = Integer.parseInt(req.getParameter("b"));
            String command = req.getParameter("op");
            int result = 0;
            String operation = "";
            switch (command) {
                case "add":
                    result = a + b;
                    operation = "+";
                    break;
                case "sub":
                    result = a - b;
                    operation = "-";
                    break;
                case "mult":
                    result = a * b;
                    operation = "*";
                    break;
                case "div":
                    result = a / b;
                    operation = "/";
                    break;
                    default: throw new RuntimeException("Invalid operation");
            }
            responseNessege = String.format("%d %s %d = %d", a, operation, b, result);
        }catch(ArithmeticException e){
            responseNessege = "You can not div on ziro";
        }catch(NumberFormatException e){
            responseNessege = "Integer conversion error";
        }catch(IllegalArgumentException e){
            responseNessege = e.getMessage();
        }catch(RuntimeException e){
            responseNessege = "Error";
        }finally {
            PrintWriter writer = resp.getWriter();
            writer.println(responseNessege);
        }
    }


}
