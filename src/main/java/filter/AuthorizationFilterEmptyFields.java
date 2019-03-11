package filter;

import org.eclipse.jetty.http.HttpMethod;
import utils.ParameterFromRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilterEmptyFields implements Filter {
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
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            ParameterFromRequest pfr = new ParameterFromRequest(req);
            String name = pfr.getStr("name");
            String surname = pfr.getStr("surname");
            String login = pfr.getStr("login");
            String password = pfr.getStr("password");
            if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty()) {
                response.getWriter().print("All fields must be filled!");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
