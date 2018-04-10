package fr.grp404.projetjee.web.servlet;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Singleton
public class PlayGameServlet extends HttpServlet {

    @Inject
    private GameDao gameDao;

    @Inject
    private UserDao userDao;

    @Inject
    private UserGameDao userGameDao;

    private final String redirectIndex = "/";
    private final String redirectGameErreur = "/startgame?erreur=0";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String gameName = request.getParameter("menu_destination");
        if(gameName.compareTo("")!=0) {
            session.setAttribute("gameName",gameName);
            User user = userDao.findByLogin((String)session.getAttribute("login"));
            Game game = gameDao.findByName(gameName);
            UserGame userGame = new UserGame(user,game);
            userGameDao.saveOrUpdate(userGame);

            request.setAttribute("gameName", gameName);
            doGet(request, response);
        }
        else{
            request.setAttribute("erreur",0);
            String toRedirect = getServletContext().getContextPath() + redirectGameErreur;
            response.sendRedirect(toRedirect);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("login")!= null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/playGame.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        else{
            String toRedirect = getServletContext().getContextPath() + redirectIndex;
            response.sendRedirect(toRedirect);
        }
    }
}
