package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersGamesListServlet extends HttpServlet {

    @Inject
    private UserGameDao userGameDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if ((boolean) session.getAttribute("admin")) {
            ServletContext sc = getServletContext();
            List<UserGame> allUsersGames = userGameDao.findAll();
            List<UserGame> usersGames = new ArrayList<>();

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
        } else {
            String toRedirect = getServletContext().getContextPath() + "/";
            response.sendRedirect(toRedirect);
        }
    }

}
