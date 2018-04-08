package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Singleton
public class ShowTestServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Inject
    private GameDao gameDao;

    @Inject
    private UserGameDao userGameDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupérer la liste des user
        List<User> users = userDao.findAll();
        List<Game> games = gameDao.findAll();
        List<UserGame> userGames = userGameDao.findAll();

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>User List</title></head>");
        out.println("<body>");
        out.println("<h1>Liste des utilisateurs fictifs</h1>");

        for (User u : users) {
            String s = "<ul><li>";
            s += u.getLogin() + "</li><li>" + u.getEmail() + "</li><li>" + u.getRole() + "</li>";
            if (u.getGame() != null) {
                s += "<li>" + u.getGame().getName() + "</li></ul>";
            } else {
                s += "</ul>";
            }

            out.println(s);
        }

        out.println("<h1>Utilisateurs du role USER</h1>");

        users = userDao.findByRole(Role.USER);
        for (User u : users) {
            String s = "<ul><li>";
            s += u.getLogin() + "</li><li>" + u.getRole() + "</li></ul>";
            out.println(s);
        }

        out.println("<h1>Utilisateurs nés le 11/10/1996</h1>");
        LocalDate d = LocalDate.of(1996, Month.OCTOBER, 11);
        users = userDao.findByBirthDate(d);
        for (User u : users) {
            String s = "<ul><li>";
            s += u.getLogin() + "</li></ul>";
            out.println(s);
        }

        out.println("<h1>Utilisateur dont l'email est : iloveage@gmail.com</h1>");
        User user = userDao.findByEmail("iloveage@gmail.com");
        String s = "<ul><li>";
        s += user.getLogin() + "</li><li>" + user.getEmail() + "</li></ul>";
        out.println(s);

        out.println("<h1>Utilisateur dont le login est : bobydo</h1>");
        user = userDao.findByLogin("bobydo");
        s = "<ul><li>";
        s += user.getLogin() + "</li></ul>";
        out.println(s);

        out.println("<h1>Liste des jeux</h1>");
        StringBuilder sb = new StringBuilder("<ul>");
        for (Game g : games) {
            sb.append("<li>").append(g.getName()).append(" players: ")
                    .append(gameDao.findNumberPlayingGame(g)).append("</li>");
        }
        sb.append("</ul>");
        out.println(sb);

        out.println("<h1>Liste des parties</h1>");
        for (UserGame ug : userGames) {
            s = "<ul><li>";
            s += ug.getGame().getName() + "</li><li>" + ug.getUser().getLogin()
                    + "</li><li>" + ug.getStartDate() + "</li><li>" + ug.getTimePlayed() + "</li></ul>";
            out.println(s);
        }


        out.println("</body></html>");
        resp.setContentType("text/html");
    }
}
