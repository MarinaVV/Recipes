package web.eng.recipes.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.eng.recipes.business_services.UserService;
import web.eng.recipes.dao.UserDaoImpl;
import web.eng.recipes.models.User;

@WebServlet(value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	UserService userService;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.sendRedirect("register/register.html");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		User user = new User();
		user.setUserName(request.getParameter("uname"));
		user.setPassword(request.getParameter("pass"));

		String isUserCreated = userService.register(user);

		switch (isUserCreated) {
		case "DUPLICATE_NAME":
			response.getWriter().print("Duplicate");
			break;
		case "ACC_CREATED":
			response.getWriter().print("Created");
			break;
		case "ERROR":
			response.getWriter().print("Created");
			break;
		}
	}

}
