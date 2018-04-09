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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Singleton
public class MemberSettingsServlet extends HttpServlet {

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    @Inject
    private UserDao userDao;


    private final String redirect = "/signin";

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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String OldLogin = req.getSession().getAttribute("login").toString();
        User user = userDao.findByLogin(OldLogin);
        Boolean err = false;

        String NewLogin = req.getParameter("login");
        String OldPassword = req.getParameter("OldPwd");
        String NewPassword = req.getParameter("NewPwd");
        String prefGame = req.getParameter("prefGame");
        String email = req.getParameter("mail");
        String birthDate = req.getParameter("birthDate");
        String error = "";

        if(!Objects.equals(NewLogin, OldLogin) && !checkLogin(NewLogin)) {
            err = true;
            error += "Le login doit être unique et faire au moins 6 caractères.<br/>";
        }

        if(!Objects.equals(OldPassword, user.getPassword())){
            err = true;
            error += "Votre ancien mot de passe est incorrect<br/>";
        }

        if(!checkPwd(NewPassword)) {
            err = true;
            error += "Le mot de passe doit faire au moins 9 caractères.<br/>";
        }

        if(!Objects.equals(email, user.getEmail()) && !checkMail(email)) {
            err = true;
            error += "L'email est incorrect.<br/>";
        }

        if(!checkBirthDate(birthDate)) {
            err = true;
            error += "La date de naissance est incorrecte.<br/>";
        }

        if(!err) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate date = LocalDate.parse(birthDate, formatter);

            if(!Objects.equals(NewPassword, user.getPassword())){
                user.setPassword(NewPassword);
            }

            if(!Objects.equals(NewLogin, user.getLogin())){
                user.setLogin(NewLogin);
            }

            if(!Objects.equals(email, user.getEmail())){
                user.setEmail(email);
            }

            if(!Objects.equals(date, user.getBirthDate())){
                user.setBirthDate(date);
            }

            userDao.saveOrUpdate(user);
            req.setAttribute("success", "Vos informations ont bien été mises à jour !");
        }

        req.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/settings.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("login")==null){
            String toRedirect = getServletContext().getContextPath() + redirect;
            response.sendRedirect(toRedirect);
        }else{
            User user = userDao.findByLogin(session.getAttribute("login").toString());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", user.getBirthDate().toString());

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/settings.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
