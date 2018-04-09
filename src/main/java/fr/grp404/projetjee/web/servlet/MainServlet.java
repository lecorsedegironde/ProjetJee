package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;

@Singleton
public class MainServlet extends HttpServlet {

    /**
     * User dao used to create admin user
     */
    @Inject
    private UserDao userDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (userDao.findAll().isEmpty()) {
            String password = Hashing.sha256().hashString("admin", StandardCharsets.UTF_8).toString();
            User u = new User("admin", password, Role.ADMIN, LocalDate.now(), "admin@admin.fr", null);
            userDao.saveOrUpdate(u);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
