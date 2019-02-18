package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    //private UsersList users;

   /* public LoginServlet(UsersList users) {
        this.users = users;
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("form.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        User user = new User(login,password);
        UsersList users = UsersList.getInstance();
        users.add(user);

        req.setAttribute("login", login);

        doGet(req, resp);
        System.out.println(UsersList.getInstance().list());
        resp.sendRedirect("/calc");
    }
}
