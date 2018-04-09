package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Singleton
public class ConnectionServlet extends HttpServlet {

    @Inject
    private UserDao userDao;


    private final String redirect = "/signin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isLegitUser = true;

        String login = request.getParameter("login");
        if (login == null || login.trim().length() == 0) {
            isLegitUser = false;
        }

        String pass = request.getParameter("password");
        if (pass == null || pass.trim().length() == 0) {
            isLegitUser = false;
        }else{
            // hash du mot de passe
            pass = Hashing.sha256()
                    .hashString(pass, StandardCharsets.UTF_8)
                    .toString();
        }

        /* Traitement de la requête et récupération du bean en résultant */

        User u = userDao.findByLogin(login);
        if (u == null) {
            isLegitUser = false;
        } else if (!u.getPassword().equals(pass)) {
            isLegitUser = false;
        }

        HttpSession session = request.getSession();

        if (!isLegitUser) {
            request.setAttribute("erreur", 0);
            session.removeAttribute("login");
            session.removeAttribute("admin");
            doGet(request, response);
        } else {

            session.setAttribute("login", login);

            if (u.getRole() == Role.ADMIN) {
                session.setAttribute("admin", true);
            } else {
                session.removeAttribute("admin");
            }

            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("login")==null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/connection.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else{
            String toRedirect = getServletContext().getContextPath() + "/";
            response.sendRedirect(toRedirect);
        }
    }
}
