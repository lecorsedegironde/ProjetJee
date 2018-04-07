package fr.grp404.projetjee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import fr.grp404.projetjee.persistence.dao.UserDao;
import fr.grp404.projetjee.persistence.dao.UserGameDao;
import fr.grp404.projetjee.persistence.domain.User;
import fr.grp404.projetjee.persistence.domain.UserGame;
//import fr.grp404.projetjee.persistence.domain.UserGame;

/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersGamesListServlet extends HttpServlet {

    @Inject
    private UserGameDao userGameDao;

    /**
     * @see HttpServlet#HttpServlet()

    public CurrentGamesListServlet() {
    super();
    // TODO Auto-generated constructor stub
    }*/

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        //List<UserGame> usersGames = userGameDao.findAll();
//		List<UserGame> usersGames = Arrays.asList(
//				new UserGame(1, 2, "01/01/18 - 18:00"),
//				new UserGame(2, 3, "01/02/18 - 18:00"),
//				new UserGame(3, 4, "01/03/18 - 18:00")
//		);
		/*List<String> usersGames = Arrays.asList(
				"01/01/18 - 18:00",
				"01/02/18 - 18:00",
				"01/03/18 - 18:00"
		);*/

//		sc.setAttribute("usersGames", usersGames);
        RequestDispatcher rd = sc.getRequestDispatcher("/usersGamesList.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
