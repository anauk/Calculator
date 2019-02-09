import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseMessage = "";

        try{
            int a = convertParameter("a",req);
            int b = convertParameter("b",req);
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

    private int convertParameter(String name, HttpServletRequest req){
        if(req.getParameter(name) == null){
            throw new IllegalStateException(String.format("Parameter %s missing",name));
        }
        return Integer.parseInt(req.getParameter(name));
    }
}
