import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ServletLogin extends HttpServlet {

    private final UserStorage security;


    public ServletLogin(UserStorage security) {
        this.security = security;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form.html"), resp.getOutputStream());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        security.register("user", "passwd");
        System.out.println(req.getParameterMap());
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        PrintWriter writer = resp.getWriter();
        writer.printf("Welcome! %s ", login);

        Cookie logC = new Cookie("login", login);
        Cookie pasC = new Cookie("password", password);
        logC.setMaxAge(60*60*24);
        pasC.setMaxAge(60*60*24);

        resp.addCookie(logC);
        resp.addCookie(pasC);

        try {
            PrintWriter w = resp.getWriter();
            Cookie c[] = req.getCookies();
            w.println("\nHello\n" + c[0].getValue());
            resp.sendRedirect("/calc");
            w.close();

        } catch (Exception e){
            e.getStackTrace();
        }

        /*if(login.equals("asda") && password.equals("qwerty")) {
            writer.println("<p>You are successfully logged in!</p>");
            System.out.printf("Login: %s, Password: %s", login, password);

            Cookie asda = new Cookie("login", login);
            resp.addCookie(asda);
            writer.println("<a>LOGOUT</a>");
        } else {
            writer.print("sorry, username or password error!");
        }*/

    }
}
