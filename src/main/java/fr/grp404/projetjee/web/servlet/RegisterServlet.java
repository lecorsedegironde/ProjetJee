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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Singleton
public class RegisterServlet extends HttpServlet{

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    @Inject
    private UserDao userDao;

    private void createUser(final String login, final String password,
                            final LocalDate birthDate, final String email) {
        User u = new User(login, password, Role.USER, birthDate, email, null);
        userDao.saveOrUpdate(u);
    }

    private boolean checkLogin(String login) {
        return userDao.findByLogin(login)==null && login.length()>5;
    }

    private boolean checkPwd(String pwd) {
        return pwd.length()>8;
    }

    private boolean checkMail(String mail) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(mail);
        return userDao.findByEmail(mail)==null && m.matches();
    }

    private boolean checkBirthDate(String birthDate) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(birthDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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

        if(!checkLogin(login)) {
            userRegistered = false;
            error += "Le login doit être unique et faire au moins 6 caractères.<br/>";
        }

        if(!checkPwd(password)) {
            userRegistered = false;
            error += "Le mot de passe doit faire au moins 9 caractères.<br/>";
        }

        if(!checkMail(mail)) {
            userRegistered = false;
            error += "L'email est incorrect.<br/>";
        }

        if(!checkBirthDate(birthDate)) {
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
