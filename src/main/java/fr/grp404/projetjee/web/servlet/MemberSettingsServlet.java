package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.web.Checker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Singleton
public class MemberSettingsServlet extends HttpServlet {

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    @Inject
    private UserDao userDao;


    private final String redirect = "/signin";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String OldLogin = req.getSession().getAttribute("login").toString();
        User user = userDao.findByLogin(OldLogin);
        Boolean err = false;

        String NewLogin = req.getParameter("login");
        String OldPassword = Hashing.sha256()
                .hashString(req.getParameter("OldPwd"), StandardCharsets.UTF_8)
                .toString();
        String NewPassword = req.getParameter("NewPwd");
        String prefGame = req.getParameter("prefGame");
        String email = req.getParameter("mail");
        String birthDate = req.getParameter("birthDate");
        String error = "";

        if(!Objects.equals(NewLogin, OldLogin) && !Checker.checkLogin(NewLogin)) {
            err = true;
            error += "Le login doit être unique et faire au moins 6 caractères.<br/>";
        }

        if(!Objects.equals(OldPassword, user.getPassword())){
            err = true;
            error += "Votre ancien mot de passe est incorrect<br/>";
        }

        if(!Checker.checkPwd(NewPassword)) {
            err = true;
            error += "Le mot de passe doit faire au moins 9 caractères.<br/>";
        }

        if(!Objects.equals(email, user.getEmail()) && !Checker.checkMail(email)) {
            err = true;
            error += "L'email est incorrect.<br/>";
        }

        if(!Checker.checkBirthDate(birthDate)) {
            err = true;
            error += "La date de naissance est incorrecte.<br/>";
        }

        if(!err) {
            NewPassword = Hashing.sha256()
                    .hashString(NewPassword, StandardCharsets.UTF_8)
                    .toString();
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
        req.setAttribute("email", user.getEmail());
        req.setAttribute("birthdate", user.getBirthDate().toString());
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
