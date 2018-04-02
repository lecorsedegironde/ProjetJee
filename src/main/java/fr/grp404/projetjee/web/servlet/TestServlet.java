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
import java.time.ZoneId;
import java.util.Date;

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
                            final Date birthDate, final String email) {
        User u = new User(login, password, role, birthDate, email);
        userDao.saveOrUpdate(u);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (userDao.findAll().isEmpty()) {
            Date d1 = Date.from(LocalDate.of(1996, Month.OCTOBER, 11).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date d2 = Date.from(LocalDate.of(1995, Month.MARCH, 12).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date d3 = Date.from(LocalDate.of(1994, Month.FEBRUARY, 28).atStartOfDay(ZoneId.systemDefault()).toInstant());
            createUser("boby", "vsnepassrezpas", Role.ADMIN, d1, "darkprotoxxx@gmail.com");
            createUser("wololo", "ageofemp", Role.USER, d2, "iloveage@gmail.com");
            createUser("guy", "argentenmasse", Role.USER, d3, "commentgagner@gmail.com");
        }

        //Construct redirection context
        String toRedirect = getServletContext().getContextPath() + redirect;
        response.sendRedirect(toRedirect);
    }
}
