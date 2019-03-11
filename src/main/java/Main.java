import utils.CookieProcessor;
import entry.UserService;
import entry.UsersList;
import filter.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        UserService us = new UserService(new UsersList());
        CookieProcessor cp = new CookieProcessor("calculator", us);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new AuthServlet(us)), "/reg/*");
        handler.addServlet(new ServletHolder(new CalculatorServlet(us,cp)), "/calc/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");


        //handler.addFilter(FilterLogin.class, "/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        //handler.addFilter(AuthorizationFilterLoginExists.class, "/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(AuthorizationFilterEmptyFields.class, "/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new AlreadyExistUserFilter(us)), "/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginPasswordFilter(us)), "/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        handler.addFilter(CalculatorFilter.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilterDivByZero.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();

    }

}
