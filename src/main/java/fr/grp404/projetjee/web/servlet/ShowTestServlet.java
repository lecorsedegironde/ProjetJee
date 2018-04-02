package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Singleton
public class ShowTestServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupérer la liste des user
        List<User> users = userDao.findAll();

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>User List</title></head>");
        out.println("<body>");
        out.println("<h1>Liste des utilisateurs fictifs</h1>");

        for (User u : users) {
            String s = "<ul><li>";
            s += u.getLogin() + "</li><li>" + u.getEmail() + "</li><li>" + u.getRole() + "</li></ul>";
            out.println(s);
        }

        out.println("</body></html>");
        resp.setContentType("text/html");
    }
}
