package servlets;

import entry.User;
import entry.UserService;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthServlet extends HttpServlet {
    private final String cookieName="calculator";
    private UserService us;

    public AuthServlet(UserService us) {
        this.us = us;
    }

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
        User user = new User(name, surname, login, password, login.hashCode());
        us.putUser(user);
        resp.sendRedirect("/login");
    }
}
