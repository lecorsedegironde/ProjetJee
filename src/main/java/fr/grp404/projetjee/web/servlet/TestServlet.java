package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

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
     * The DAO used to access user
     */
    @Inject
    private GameDao gameDao;

    /**
     * The DAO used to access user
     */
    @Inject
    private UserGameDao userGameDao;

    /**
     * Precise the servlet redirected to after init
     */
    private final String redirect = "/show";

    private void createUser(final String login, final String password, final Role role,
                            final LocalDate birthDate, final String email, final Game fav) {
        User u = new User(login, password, role, birthDate, email, fav);
        userDao.saveOrUpdate(u);
    }

    private void createUser(final String login, final String password, final Role role,
                            final LocalDate birthDate, final String email) {
        User u = new User(login, password, role, birthDate, email, null);
        userDao.saveOrUpdate(u);
    }

    private void createGame(final String name) {
        Game g = new Game(name);
        gameDao.saveOrUpdate(g);
    }

    private void createUserGame(final User u, final Game g) {
        UserGame ug = new UserGame(u, g);
        userGameDao.saveOrUpdate(ug);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (gameDao.findAll().isEmpty()) {
            createGame("Mario");
            createGame("Tetris");
            createGame("Rayman");
        }

        if (userDao.findAll().isEmpty()) {
            LocalDate d1 = LocalDate.of(1996, Month.OCTOBER, 11);
            LocalDate d2 = LocalDate.of(1995, Month.MARCH, 12);
            LocalDate d3 = LocalDate.of(1994, Month.FEBRUARY, 28);
            createUser("bobydo", "vsnepassrezpas", Role.ADMIN, d1,
                    "darkprotoxxx@gmail.com", gameDao.findByName("Mario"));
            createUser("wololo", "ageofemp", Role.USER, d2,
                    "iloveage@gmail.com", gameDao.findByName("Rayman"));
            createUser("guygolo", "argentenmasse", Role.USER, d3, "commentgagner@gmail.com");
            createUser("guygolo22", "argentenmasse2", Role.USER, d3, "commentgagner2@gmail.com");
        }


        if (userGameDao.findAll().isEmpty()) {
            createUserGame(userDao.findByLogin("wololo"), gameDao.findByName("Mario"));
            createUserGame(userDao.findByLogin("guygolo"), gameDao.findByName("Tetris"));
            createUserGame(userDao.findByLogin("guygolo22"), gameDao.findByName("Tetris"));
            createUserGame(userDao.findByLogin("bobydo"), gameDao.findByName("Rayman"));
        }

        //Construct redirection context
        String toRedirect = getServletContext().getContextPath() + redirect;
        resp.sendRedirect(toRedirect);
    }
}
