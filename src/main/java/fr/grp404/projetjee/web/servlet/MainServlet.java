package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Singleton
public class MainServlet extends HttpServlet {

    /**
     * User dao used to create admin user
     */
    @Inject
    private UserDao userDao;

    /**
     * Game dao used to create some games
     */
    @Inject
    private GameDao gameDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (userDao.findAll().isEmpty()) {
            String password = Hashing.sha256().hashString("admin", StandardCharsets.UTF_8).toString();
            User u = new User("adminUser", password, Role.ADMIN, LocalDate.now(), "admin@admin.fr", null);
            userDao.saveOrUpdate(u);
        }

        if (gameDao.findAll().isEmpty()) {
            Game g1 = new Game("Mario");
            gameDao.saveOrUpdate(g1);
            Game g2 = new Game("Sonic");
            gameDao.saveOrUpdate(g2);
            Game g3 = new Game("Pong");
            gameDao.saveOrUpdate(g3);
            Game g4 = new Game("Tetris");
            gameDao.saveOrUpdate(g4);
            Game g5 = new Game("Age of Empire");
            gameDao.saveOrUpdate(g5);
        }

        List<Game> games = gameDao.findAll();
        request.setAttribute("games", games);
        HashMap<String, Integer> gamePlayers = new HashMap<>();
        for (Game g : games) {
            gamePlayers.put(g.getName(), gameDao.findNumberPlayingGame(g));
        }

        request.setAttribute("gamePlayers", gamePlayers);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
