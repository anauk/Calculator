package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form_calc.html"), resp.getOutputStream());
        req.getCookies();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int a = pfr.getInt("a");
        int b = pfr.getInt("b");
        String op = pfr.getStr("op");
        int result=0;
        String operation="";
        switch (op){
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
        }
        PrintWriter writer = resp.getWriter();
        writer.printf("%d %s %d = %d", a, operation, b, result);
    }
}
