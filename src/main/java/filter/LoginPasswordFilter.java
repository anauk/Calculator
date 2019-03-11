package filter;

import org.eclipse.jetty.http.HttpMethod;
import utils.ElementNotFoundInUserListException;
import utils.ParameterFromRequest;
import entry.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginPasswordFilter implements Filter {
    private UserService us;

    public LoginPasswordFilter(UserService us) {
        this.us = us;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }

        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");


        try {
            if (!us.checkUserPass(login, password)) {
                throw new IllegalArgumentException("The user password is incorrect");
            }
            chain.doFilter(request, response);
        } catch (IllegalArgumentException | ElementNotFoundInUserListException e) {
            response.getWriter().println("<html>" +
                    "<a href=\"/login\">The user login or password is incorrect. Click on the link and enter correctly login or password!</a>" +
                    "</html>");
        }
    }


    @Override
    public void destroy() {

    }
}
