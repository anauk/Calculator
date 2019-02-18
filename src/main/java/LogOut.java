import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* final HttpServlet session = (HttpServlet) req.getSession();
        //session.invalidate();
         ((HttpSession) session).removeAttribute("login");
         ((HttpSession) session).removeAttribute("password");
         resp.sendRedirect("/");
         resp.getWriter().print("You are successfully logged out!");*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cookie> cookies = Arrays.asList(req.getCookies());
        if(cookies !=null){
            cookies.stream()
                    .map((Function<Cookie, Cookie>) c -> new Cookie(c.getName(), c.getValue()) {{ setMaxAge(0);}})
                    .forEach(resp::addCookie);
        }
    }
}
