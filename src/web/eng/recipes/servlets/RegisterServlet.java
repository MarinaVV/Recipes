package web.eng.recipes.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.eng.recipes.dao.UsersDao;
import web.eng.recipes.models.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").println();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		UsersDao dao = new UsersDao();
		User user = new User();
		user.setUserName(request.getParameter("uname"));
		user.setPassword(request.getParameter("pass"));

		if (dao.findUserByUsername(user.getUserName()) != null) {
			response.getWriter().println("name exist");
		} else {
			if (dao.createUser(user)) {
				response.getWriter().println("Acc Created");
			} else {
				response.getWriter().println("Error");
				
			}
		}
	}

}
