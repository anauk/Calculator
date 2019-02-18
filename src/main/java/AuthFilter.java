import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null) {   //checking whether the session exists
            this.context.log("Unauthorized access request");

        } else {
            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        }

        /* System.out.println("doFilter is execute");
        HttpServletRequest sr = (HttpServletRequest) servletRequest;
        HttpSession session = sr.getSession();
        //получаем путь к странице с логином
        String uriPath = sr.getRequestURI().substring(sr.getContextPath().length());
        System.out.printf("context path: %s, path: %s", sr.getContextPath(), uriPath);
        if(session == null|| session.getAttribute("login")==null){
            // надо получить текущий url, чтобы по нему перенаправить потом с login.jsp
            HttpServletResponse sres = (HttpServletResponse) servletResponse;  // Мы не можем вызвать response.sendRedirect("login.jsp") так как нам нужен httpResponse, а не ServletResponse.

        if ("login".equals(uriPath) ||  "logout".equals(uriPath)) {
            filterChain.doFilter(servletRequest, servletResponse);  // вызываем следующий фильтр.
        }
        // fixme надо добавить destination
        sres.sendRedirect("login?destination=" + uriPath);
        return;
    }
        filterChain.doFilter(servletRequest, servletResponse);  // вызываем следующий фильтр.*/
}


    @Override
    public void destroy() {

    }
}
