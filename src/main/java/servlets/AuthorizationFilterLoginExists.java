package servlets;

import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilterLoginExists implements Filter {
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
            String login = pfr.getStr("login");
           /* Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = req.getCookies();
            if(cookies != null){
                System.out.println("Cookies:");
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                   if(cookie.getName().equals(login)){
                       System.out.println("This login is already exists!");
                   }else {
                       chain.doFilter(request, response);
                   }
                }
            } else {
                System.out.println("No cookies found!");
            }
        } else {
            chain.doFilter(request, response);
        }*/
           /* if(UsersList.getInstance().list().contains(login)){
                response.getWriter().print("This login is already exists!");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
            }*/
        if(login.equals("asda")) {
                response.getWriter().print("This login is already exists!");
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
