import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = req.getCookies();
        if(cookies != null){
            System.out.println("Cookies:");
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                System.out.printf("Name: %s , Value: %s", cookie.getName(), cookie.getValue());
                System.out.println("\n");
            }
        } else {
            System.out.println("No cookies found!");
        }
    }
}
