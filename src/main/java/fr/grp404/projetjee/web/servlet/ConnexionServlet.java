package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class ConnexionServlet extends HttpServlet {

    @Inject
    private UserDao userDao;


    private final String redirect = "/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isLegitUser = true;

        String login = request.getParameter("login");
        if (login == null || login.trim().length() == 0) {
            isLegitUser = false;
        }

        String pass = request.getParameter("password");
        if (pass == null || pass.trim().length() == 0) {
            isLegitUser = false;
        }

        /* Traitement de la requête et récupération du bean en résultant */

        User u = userDao.findByLogin(login);
        if (u == null) {
            isLegitUser = false;
        } else if (!u.getPassword().equals(pass)) {
            isLegitUser = false;
        }

        if (!isLegitUser) {
            doGet(request, response);
        } else {
            HttpSession session = request.getSession();

            session.setAttribute("login", login);


            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toRedirect = getServletContext().getContextPath() + redirect;
        response.sendRedirect(toRedirect);
    }
}
