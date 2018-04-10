package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class AddUserGameServlet extends HttpServlet {

    @Inject
    private GameDao gameDao;

    @Inject
    private UserDao userDao;

    @Inject
    private UserGameDao userGameDao;

    private final String redirect = "/startgame";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = userDao.findByLogin((String) session.getAttribute("login"));
        Game game = gameDao.findByName((String) session.getAttribute("gameName"));
        UserGame userGame = userGameDao.findCurrentByUser(user);
        userGame.stop();
        userGameDao.saveOrUpdate(userGame);

        session.removeAttribute("dateDebut");
        session.removeAttribute("gameName");
        String toRedirect = getServletContext().getContextPath() + redirect;
        response.sendRedirect(toRedirect);
    }
}
