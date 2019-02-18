import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req;
        if (servletRequest instanceof HttpServletRequest) {
            req = (HttpServletRequest)servletRequest;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }

        if (HttpMethod.POST.name().equals(req.getMethod())) {

                ParameterFromRequest pfr = new ParameterFromRequest(req);
                String login = pfr.getStr("login");
                String password = pfr.getStr("password");
                if(login.equals("asda") && password.equals("qwerty")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    servletResponse.getWriter().print("Your login or password wrong");

                }


        } else {
            filterChain.doFilter(servletRequest, servletResponse);

        }
    }

    @Override
    public void destroy() {

    }
}
