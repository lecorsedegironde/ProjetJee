package fr.grp404.projetjee.web.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Singleton
public class MemberPageServlet extends HttpServlet {

    @Inject
    private UserDao userDao;
    @Inject
    private UserGameDao userGameDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null || !((boolean) session.getAttribute("admin"))) {
            String toRedirect = getServletContext().getContextPath() + "/signin";
            response.sendRedirect(toRedirect);
        } else {
            User user = userDao.findByLogin(request.getParameter("login"));
            LocalDate birthDate = user.getBirthDate();
            String date = birthDate.getDayOfMonth() + " "
                    + birthDate.getMonth() + " "
                    + birthDate.getYear();
            request.setAttribute("login", user.getLogin());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", date);
            request.setAttribute("banned", user.isBan());
            request.setAttribute("game", userGameDao.findCurrentByUser(user).getGame().getName());

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/member.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
