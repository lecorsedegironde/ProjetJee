package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersGamesListServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Inject
    private GameDao gameDao;

    @Inject
    private UserGameDao userGameDao;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*if (gameDao.findByName("Luigi") == null) {
            Game g1 = new Game("Luigi");
            gameDao.saveOrUpdate(g1);
            Game g2 = new Game("Sonic");
            gameDao.saveOrUpdate(g2);
            Game g3 = new Game("Pong");
            gameDao.saveOrUpdate(g3);

            String password = Hashing.sha256().hashString("user", StandardCharsets.UTF_8).toString();
            User u = new User("userTest", password, Role.USER, LocalDate.now(), "user@admin.fr", null);
            userDao.saveOrUpdate(u);

            UserGame userGame = new UserGame(u, g1);
            userGameDao.saveOrUpdate(userGame);
            userGame = new UserGame(u, g2);
            userGameDao.saveOrUpdate(userGame);
            userGame = new UserGame(u, g3);
            userGameDao.saveOrUpdate(userGame);
        }*/

        ServletContext sc = getServletContext();
        List<UserGame> allUsersGames = userGameDao.findAll();
        List<UserGame> usersGames = new ArrayList<UserGame>();

        String params[] = request.getParameterValues("stop");
        if (params != null) {
            if (params.length > 0) {
                int i = 0;
                boolean stopped = false;
                int id = Integer.valueOf(params[0]);
                while (i < allUsersGames.size() && !stopped) {
                    UserGame userGame = allUsersGames.get(i);
                    if (userGame.getId() == id) {
                        userGame.stop();
                        userGameDao.saveOrUpdate(userGame);
                        stopped = true;
                    }
                    i++;
                }
            }
        }

        for (UserGame userGame : allUsersGames) {
            if (userGame.getEndDate() == null) {
                usersGames.add(userGame);
            }
        }

        sc.setAttribute("usersGames", usersGames);
        RequestDispatcher rd = sc.getRequestDispatcher("/usersGamesList.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
