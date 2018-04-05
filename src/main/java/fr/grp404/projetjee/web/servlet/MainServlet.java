package fr.grp404.projetjee.web.servlet;

import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        if (login == null || login.trim().length() == 0) {
            login = null;
        }

        String pass = request.getParameter("pass");
        if (pass == null || pass.trim().length() == 0) {
            pass = null;
        }

        if (pass == null || login == null) {
            doGet(request, response);
        }

        /* Traitement de la requête et récupération du bean en résultant */

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        session.setAttribute("login", login);
        session.setAttribute("pass", pass);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
