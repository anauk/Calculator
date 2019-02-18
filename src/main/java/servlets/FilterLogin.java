package servlets;

import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterLogin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest)request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())){
            ParameterFromRequest pfr = new ParameterFromRequest(req);
            String login = pfr.getStr("login");
            String password = pfr.getStr("password");
            if(login.equals("asda")) {
                chain.doFilter(request, response);
            } else {
                System.out.println(UsersList.getInstance().list());
                response.getWriter().print("<html><p>Your login or password wrong!<p><a href='/login'>Authorization</a></html>");
            }
        } else {
            chain.doFilter(request, response);

        }
    }

    @Override
    public void destroy() {

    }
}
