import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {
        ServletExamples servlet1 = new ServletExamples();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(servlet1), "/admin/*");
        handler.addServlet(new ServletHolder(new ServletLogin()), "/login/*");
        handler.addServlet(new ServletHolder(new ServletCalculator()), "/calc/*");
        handler.addServlet(new ServletHolder(new ServletCookies()), "/c/*");
        handler.addFilter(CalculatorFilter.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilterDivByZero.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(AuthFilter.class,"/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new SignInServlet(new AccountServices())),"/auth/signin");
        handler.addServlet(new ServletHolder(new SignUpServlet(new AccountServices())),"/auth/signup");

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
