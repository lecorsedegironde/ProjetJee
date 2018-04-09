package fr.grp404.projetjee.web.servlet;

import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class LogOutServlet extends HttpServlet {

    private final String redirect = "/";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        String toRedirect = getServletContext().getContextPath() + redirect;
        response.sendRedirect(toRedirect);
    }
}
