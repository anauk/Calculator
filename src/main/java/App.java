import database.DBConnection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {
        ServletExamples servlet1 = new ServletExamples();
        ServletContextHandler handler = new ServletContextHandler();
        Connection connection = new DBConnection().connection();

        handler.addServlet(new ServletHolder(new ServletCookies()), "/c/*");
        handler.addServlet(new ServletHolder(servlet1), "/admin/*");
        handler.addServlet(new ServletHolder(new ServletLogin()), "/login/*");
        handler.addFilter(CalculatorFilter.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilterDivByZero.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
      handler.addFilter(LoginFilter.class, "/login/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
      //  handler.addFilter(AuthFilter.class, "/login/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new LogOut()), "/logout/*");
        handler.addServlet(new ServletHolder(new GetCookies()), "/get/*");
        handler.addServlet(new ServletHolder(new FormLogin()), "/form/*");
        handler.addServlet(new ServletHolder(new ServletCalculator(connection)), "/calc/*");
        handler.addServlet(new ServletHolder(new ServletHistory(connection)), "/calc/history/*");




        //handler.addServlet(new ServletHolder(new AuthServlet()), "/auth/*");


        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
