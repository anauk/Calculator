import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // full URI = getServletPath + getPathInfo
        String q1 = req.getRequestURI();
        // only servlet path
        String q2 = req.getServletPath();
        // only request after path
        String q3 = req.getPathInfo();
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);

        // how to access to parameters
        Map<String, String[]> parameterMap = req.getParameterMap();
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String op = req.getParameter("op");

        // how to access to cookies
        Cookie[] cookies = req.getCookies();

        // how to access to HTTP headers
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String sn = headerNames.nextElement();
            String sv = req.getHeader(sn);
            System.out.printf("%s: %s\n", sn, sv);
        }

        // how to write the response to server
        BufferedReader br = new BufferedReader(new FileReader(new File("1.txt")));

        PrintWriter writer = resp.getWriter();
        String textContent = br.lines().collect(Collectors.joining());
        writer.println();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("POST rq");
    }
}
