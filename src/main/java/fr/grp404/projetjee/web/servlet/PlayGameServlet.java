package fr.grp404.projetjee.web.servlet;


import com.google.inject.Singleton;

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

    private final String redirectIndex = "/";
    private final String redirectGameErreur = "/startgame?erreur=0";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String gameName = request.getParameter("menu_destination");
        if(gameName != "") {
            request.setAttribute("gameName", gameName);
            session.setAttribute("gameName",gameName);
            session.setAttribute("dateDebut", LocalDateTime.now());
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
