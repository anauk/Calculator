import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServletCookies extends HttpServlet {
    private final String COOKIE_NAME = "MY_cookie";

    /**
     *
     * How to set cookie:
     *    resp.addCookie(new Cookie(name, value));
     *
     * How to remove cookie:
     *   Cookie cookie = new Cookie(name, value);
     *   cookie.setMaxAge(0);
     *   resp.addCookie(cookie);
     *
     * How to read cookie:
     *   Cookie[] cookies = req.getCookies();
     *   if (cookies != null && cookies.length > 0) {
     *       for (Cookie c: cookies) {
     *           String name = c.getName();
     *           String value = c.getValue();
     *       }
     *   }
     *
     */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cmd = req.getPathInfo();
        List<Cookie> myCookies = Arrays
                .stream(req.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(COOKIE_NAME))
                .collect(Collectors.toList());
        PrintWriter w = resp.getWriter();
        switch (cmd) {
            case "/set": resp.addCookie(new Cookie(COOKIE_NAME, "Hello:"+(int)(Math.random()*100)));
                break;
            case "/get": myCookies.forEach(c -> w.printf("Cookie_name:%s, value:%s", c.getName(), c.getValue()));
                break;
            case "/rm": myCookies.stream()
                    .map((Function<Cookie, Cookie>) c -> new Cookie(c.getName(), c.getValue()) {{ setMaxAge(0);}})
                    .forEach(resp::addCookie);
                break;
        }
    }
}
