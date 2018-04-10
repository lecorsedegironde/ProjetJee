package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.impl.GameDaoImpl;
import fr.grp404.projetjee.persistence.domain.Game;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Singleton
public class GameServlet extends HttpServlet {

    @Inject
    private GameDaoImpl gameDao;


    private final String redirect = "/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameName = request.getParameter("gameName");
        Game g = new Game(gameName);
        gameDao.saveOrUpdate(g);

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if ((boolean) session.getAttribute("admin")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/games.jsp");
            try {
                List<Game> listGames = gameDao.findAll();
                HashMap<String, Integer> gamePlayers = new HashMap<>();
                for (Game g : listGames) {
                    gamePlayers.put(g.getName(), gameDao.findNumberPlayingGame(g));
                }

                request.setAttribute("listGames", listGames);
                request.setAttribute("gamePlayers", gamePlayers);
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String toRedirect = getServletContext().getContextPath() + redirect;
            response.sendRedirect(toRedirect);
        }
    }
}
