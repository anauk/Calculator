package filter;

import org.eclipse.jetty.http.HttpMethod;
import utils.ElementNotFoundInUserListException;
import utils.ParameterFromRequest;
import entry.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlreadyExistUserFilter implements Filter {
    private UserService us;

    public AlreadyExistUserFilter(UserService us) {
        this.us = us;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        HttpServletResponse resp;
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            req = (HttpServletRequest) request;
            resp = (HttpServletResponse) response;
        } else {
            throw new IllegalArgumentException("ServletRequest and ServletResponse should be instance of HttpServletRequest and HttpServletResponse");
        }
        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        if(us.isEmpty()){
            chain.doFilter(request, response);
        }
        try {
            us.getUser(login.hashCode());
            String error = "The user with this login is already exist";
            response.getWriter().printf("<html> <a href=\"/reg\"> %s </a></html>", error);
        } catch (ElementNotFoundInUserListException e){
            try {
                chain.doFilter(request, response);
            }catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {

    }
}
