import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class App {
    public static void main(String[] args) throws Exception {
        Servlet1 servlet1 = new Servlet1();

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(servlet1), "/admin/*");
        handler.addServlet(new ServletHolder(new ServletCalc()), "/calc/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
