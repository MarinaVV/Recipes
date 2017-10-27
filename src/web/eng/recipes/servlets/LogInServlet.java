package web.eng.recipes.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.eng.recipes.dao.UserDaoImpl;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/log-in.jsp");
		dispatcher.forward(request, response);
		// response.sendRedirect("log-in/log-in.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserDaoImpl dao = new UserDaoImpl();

		String username = request.getParameter("uname");

		if (dao.findUserByUsername(username) != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/home-page.jsp");
			request.setAttribute("uname", username);
			dispatcher.forward(request, response);
			// response.sendRedirect("WEB_INF/home-page/home-page.jsp?uname=" +
			// username);
		} else {
			response.sendRedirect("register/register.html");
		}
		// doGet(request, response);
	}

}
