import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    public static void main(String[] args) throws Exception {
        ServletExamples servlet1 = new ServletExamples();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(servlet1), "/admin/*");
        handler.addServlet(new ServletHolder(new ServletLogin()), "/login/*");
        handler.addServlet(new ServletHolder(new ServletCalculator()), "/calc/*");

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
