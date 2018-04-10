package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.impl.GameDaoImpl;
import fr.grp404.projetjee.persistence.dao.impl.UserGameDaoImpl;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Singleton
public class UserGameEndServlet extends HttpServlet {

    @Inject
    private UserGameDaoImpl userGameDao;


    private final String redirect = "/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if((boolean)session.getAttribute("admin")){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/userGamesEnd.jsp");
            try {
                List<UserGame> listGame = userGameDao.findAll();
                request.setAttribute("listGame" , listGame);
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        else{
            String toRedirect = getServletContext().getContextPath() + redirect;
            response.sendRedirect(toRedirect);
        }
    }
}
