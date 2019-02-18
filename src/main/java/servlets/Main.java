package servlets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new CalculatorServlet()), "/calc/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new AuthorizationServlet()), "/auth/*");
        handler.addFilter(FilterLogin.class, "/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(AuthorizationFilterLoginExists.class, "/auth/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(AuthorizationFilterEmptyFields.class, "/auth/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();

    }

}
