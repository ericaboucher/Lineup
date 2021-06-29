package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1787L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect data from login form 
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//call dao to validate user
		ApplicationDao dao = new ApplicationDao();
		boolean isValidUser = dao.validateUser(email, password); //need method

		//check to see if user is valid
		if(isValidUser) {
			//set up HTTP session
			HttpSession session = request.getSession();

			//set username as attribute
			session.setAttribute("email", email);
			//forward to index page?? home page??
			//request.getRequestDispatcher("/index.html").forward(request, response);		
		} else {
			String errorMessage = "Sorry, email or password is not valid. Please try again.";
					request.setAttribute("error", errorMessage);
					request.getRequestDispatcher("/index.html").forward(request, response);
		}

	}

	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	*/

}