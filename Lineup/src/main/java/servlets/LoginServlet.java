package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.ApplicationDao;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 17871L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect data from login form 
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//call dao to validate user
		ApplicationDao dao = new ApplicationDao();
		boolean isValidUser = dao.validateUser(email, password); 

		//check to see if user is valid
		if(isValidUser) {
			//set up HTTP session
			//HttpSession session = request.getSession();
		    ServletContext context = request.getServletContext();

			//set username as attribute
//			session.setAttribute("email", email);
		    User currentUser = dao.readUser(email);
		    context.setAttribute("user", currentUser);
			request.getRequestDispatcher("/home.html").forward(request, response);		
		} else {
			String errorMessage = "Sorry, email or password is not valid. Please try again.";
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("/index.html").forward(request, response);
		}

	}


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
		dispatcher.include(request, response);
	}
}

