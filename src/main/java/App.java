import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    public static void main(String[] args) throws Exception {
        Servlet1 servlet1 = new Servlet1();

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(servlet1), "/admin/*");

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
