import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletCalculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String responseMessage = "";
        try{
            int a = pfr.get("a");
            int b = pfr.get("b");
            String command = req.getParameter("op");
            int result=0;
            String operation="";
            switch(command){
                case "add": result = a+b;
                    operation = "+";
                    break;
                case "sub": result = a-b;
                    operation = "-";
                    break;
                case "mul": result = a*b;
                    operation = "*";
                    break;
                case "div": result = a/b;
                    operation = "/";
                    break;
                default: throw new RuntimeException("Invalid operation");
            }
            responseMessage = String.format("%d %s %d = %d", a, operation, b, result);
        }catch (ArithmeticException e){
            responseMessage = "You can't divide by zero";
        }catch (NumberFormatException e){
            responseMessage = "Integer conversion error";
        }catch (IllegalStateException e){
            responseMessage = e.getMessage();
        }catch (RuntimeException e){
            responseMessage = e.getMessage();
        }
        finally {
            PrintWriter writer = resp.getWriter();
            writer.println(responseMessage);
        }
    }
}
