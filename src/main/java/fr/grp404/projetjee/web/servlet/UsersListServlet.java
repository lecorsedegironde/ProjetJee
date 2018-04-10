package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersListServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*if (userDao.findByLogin("usertest1") == null) {

            String password = Hashing.sha256().hashString("user1", StandardCharsets.UTF_8).toString();
            User u = new User("usertest1", password, Role.USER, LocalDate.now(), "user1@admin.fr", null);
            userDao.saveOrUpdate(u);
            password = Hashing.sha256().hashString("user1", StandardCharsets.UTF_8).toString();
            u = new User("usertest2", password, Role.USER, LocalDate.now(), "user2@admin.fr", null);
            userDao.saveOrUpdate(u);
            password = Hashing.sha256().hashString("user3", StandardCharsets.UTF_8).toString();
            u = new User("usertest3", password, Role.USER, LocalDate.now(), "user3@admin.fr", null);
            userDao.saveOrUpdate(u);
        }*/

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
    }
}
