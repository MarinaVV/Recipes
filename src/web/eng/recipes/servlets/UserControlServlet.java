package web.eng.recipes.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.eng.recipes.business_services.UserService;
import web.eng.recipes.models.User;

@WebServlet("/UserControlServlet")
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject UserService userService;

	public UserControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");

		if (action!=null && action.equals("register")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		} else {

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/log-in.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		User user = new User();
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		if(action!=null && action.equals("log_in")){
			
			boolean isLogInSuccsesful =  userService.login(user) ;
			
			if (isLogInSuccsesful) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/NavBarControlServlet?action=home");
				request.setAttribute("uname", user.getUserName());
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/log-in.jsp");
				request.setAttribute("is_invalid", "true");
				dispatcher.forward(request, response);
			}
			
		}else if(action!=null && action.equals("register")){
			
			String isUserCreated = userService.register(user);
			
			response.getWriter().write(isUserCreated);

			/*switch (isUserCreated) {
			case "DUPLICATE_NAME":
				response.getWriter().write("Duplicate");
				break;
			case "ACC_CREATED":
				response.getWriter().write("ACC_CREATED");
				break;
			case "ERROR":
				response.getWriter().write("Created");
				break;
			}*/
		}
		
	}

}
