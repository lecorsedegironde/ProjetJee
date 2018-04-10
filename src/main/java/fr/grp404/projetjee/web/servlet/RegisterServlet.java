package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.GameDao;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.Game;
import fr.grp404.projetjee.persistence.domain.Role;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.web.Checker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Singleton
public class RegisterServlet extends HttpServlet{

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    @Inject
    private UserDao userDao;
    @Inject
    private GameDao gameDao;

    private void createUser(final String login, final String password,
                            final LocalDate birthDate, final String email) {
        User u = new User(login, password, Role.USER, birthDate, email, null);
        userDao.saveOrUpdate(u);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Game> games = gameDao.findAll();
        req.setAttribute("games", games);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        boolean userRegistered = true;

        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String prefGame = req.getParameter("prefGame");
        String mail = req.getParameter("mail");
        String birthDate = req.getParameter("birthDate");
        String error = "";

        if(userDao.findByLogin(login)==null || !Checker.checkLogin(login)) {
            userRegistered = false;
            error += "Le login doit être unique et faire au moins 6 caractères.<br/>";
        }

        if(!Checker.checkPwd(password)) {
            userRegistered = false;
            error += "Le mot de passe doit faire au moins 9 caractères.<br/>";
        }

        if(userDao.findByEmail(mail)==null || !Checker.checkMail(mail)) {
            userRegistered = false;
            error += "L'email est incorrect.<br/>";
        }

        if(!Checker.checkBirthDate(birthDate)) {
            userRegistered = false;
            error += "La date de naissance est incorrecte.<br/>";
        }

        if(userRegistered) {
            password = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate date = LocalDate.parse(birthDate, formatter);
            createUser(login, password, date, mail);
            req.setAttribute("success", "Vous êtes inscrit, bienvenue !");
        }

        req.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
