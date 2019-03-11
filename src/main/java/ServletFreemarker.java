
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import peoples.Person;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;


public class ServletFreemarker extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();

        ArrayList<Integer> a = new ArrayList<>();
        a.add(11);
        a.add(13);
        a.add(15);
        ArrayList<Person> a2 = new ArrayList<>();
        a2.add(new Person(1, "Mike"));
        a2.add(new Person(2, "John"));
        a2.add(new Person(3, "Kate"));


        HashMap<String, Object> data = new HashMap<>();
        data.put("name1", "Alex");
        data.put("name2", "DIMA");
        data.put("dataForList", a);
        data.put("data3", a2);
        //data.put("questions", "Q");

        f.render("InjectionExample.ftl", data, resp);

    }
}
