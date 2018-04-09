package fr.grp404.projetjee.web.servlet;

import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
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
public class MemberPageServlet extends HttpServlet {

    @Inject
    private UserDao userDao;
    @Inject
    private UserGameDao userGameDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("login")==null || !((boolean) session.getAttribute("admin"))){
            String toRedirect = getServletContext().getContextPath() + "/signin";
            response.sendRedirect(toRedirect);
        }else{
            User user = userDao.findByLogin(request.getParameter("login"));
            request.setAttribute("login", user.getLogin());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", user.getBirthDate().toString());
            request.setAttribute("game", userGameDao.findCurrentByUser(user));

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/member.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}