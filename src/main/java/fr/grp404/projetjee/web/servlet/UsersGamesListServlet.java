package fr.grp404.projetjee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import fr.grp404.projetjee.persistence.domain.User;
//import fr.grp404.projetjee.persistence.domain.UserGame;

/**
 * Servlet implementation class CurrentGamesListServlet
 */
@Singleton
public class UsersGamesListServlet extends HttpServlet {
	
	//@Inject
    //private UserGameDao userGameDao;
       
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
        //List<UserGame> g = userGameDao.findAll();
		String userGame[] = {"Mario", "pseudo1", "01/01/18 - 18:00"};
		//UserGame currGame = new UserGame(1, "Mario", "01/01/18 - 18:00");
				
		ServletContext sc = getServletContext();
		//sc.setAttribute("currGame", userGame);
		sc.setAttribute("name", "pseudo1");
		RequestDispatcher rd = sc.getRequestDispatcher("/usersGamesList.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
