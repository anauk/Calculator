package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("formAuth.html"), resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getStr("name");
        String surname = pfr.getStr("surname");
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        int id = 0;
        User user = new User(name, surname, login, password, id);
        UsersList users = UsersList.getInstance();
        users.add(user);
        users.saveUser(user);
        req.setAttribute("login", login);

        doGet(req, resp);

        System.out.println(users.list());
        resp.addCookie(new Cookie(login,password));
        System.out.println((new Cookie(login,password)).getValue());
        resp.sendRedirect("/calc");
    }
}
