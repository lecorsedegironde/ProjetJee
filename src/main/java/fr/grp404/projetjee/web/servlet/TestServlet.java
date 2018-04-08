package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

//Obligatoire sinon Guice va raler
@Singleton
public class TestServlet extends HttpServlet {

    /**
     * The DAO used to access user
     */
    @Inject
    private UserDao userDao;

    /**
     * Precise the servlet redirected to after init
     */
    private final String redirect = "/show";

    private void createUser(final String login, final String password, final Role role,
                            final LocalDate birthDate, final String email) {
        User u = new User(login, password, role, birthDate, email);
        userDao.saveOrUpdate(u);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (userDao.findAll().isEmpty()) {
            LocalDate d1 = LocalDate.of(1996, Month.OCTOBER, 11);
            LocalDate d2 = LocalDate.of(1995, Month.MARCH, 12);
            LocalDate d3 = LocalDate.of(1994, Month.FEBRUARY, 28);
            createUser("bobydo", "vsnepassrezpas", Role.ADMIN, d1, "darkprotoxxx@gmail.com");
            createUser("wololo", "ageofemp", Role.USER, d2, "iloveage@gmail.com");
            createUser("guygolo", "argentenmasse", Role.USER, d3, "commentgagner@gmail.com");
        }

        //Construct redirection context
        String toRedirect = getServletContext().getContextPath() + redirect;
        resp.sendRedirect(toRedirect);
    }
}
