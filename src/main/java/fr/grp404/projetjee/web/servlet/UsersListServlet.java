package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersListServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ((boolean) session.getAttribute("admin")) {

            ServletContext sc = getServletContext();
            List<User> users = userDao.findAll();

            String params[] = request.getParameterValues("ban");
            if (params != null) {
                if (params.length > 0) {
                    int i = 0;
                    boolean banned = false;
                    int id = Integer.valueOf(params[0]);
                    while (i < users.size() && !banned) {
                        User user = users.get(i);
                        if (user.getId() == id) {
                            user.ban();
                            userDao.saveOrUpdate(user);
                            banned = true;
                        }
                        i++;
                    }
                }
            }

            params = request.getParameterValues("unban");
            if (params != null) {
                if (params.length > 0) {
                    int i = 0;
                    boolean unbanned = false;
                    int id = Integer.valueOf(params[0]);
                    while (i < users.size() && !unbanned) {
                        User user = users.get(i);
                        if (user.getId() == id) {
                            user.unBan();
                            userDao.saveOrUpdate(user);
                            unbanned = true;
                        }
                        i++;
                    }
                }
            }

            sc.setAttribute("users", users);
            RequestDispatcher rd = sc.getRequestDispatcher("/users.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String redirect = "/";
            String toRedirect = getServletContext().getContextPath() + redirect;
            response.sendRedirect(toRedirect);
        }
    }
}
