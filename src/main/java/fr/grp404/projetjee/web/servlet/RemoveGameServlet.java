package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.domain.Game;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class RemoveGameServlet extends HttpServlet {

    @Inject
    private GameDao gameDao;


    private final String redirect = "/game";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameName = request.getParameter("gameName");
        gameDao.deleteGame(gameDao.findByName(gameName));

        String toRedirect = getServletContext().getContextPath() + redirect;
        response.sendRedirect(toRedirect);
    }
}
