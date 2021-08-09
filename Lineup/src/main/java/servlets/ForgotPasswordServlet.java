package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/forgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String infoMessage = null;

    public ForgotPasswordServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect data from form
		String email = request.getParameter("email");
		
		//check database for email
		boolean isValidEmail = UserDao.validateEmail(email); 
		
		//if email is in db
		if (isValidEmail) {
			infoMessage = "Password recovery e-mail sent to " + email;
		}
		else {
			infoMessage = "Sorry, that user is not registered. Please contact your child's school for further assistance.";
		}
		//write the message back to user
	 	String destination = "/forgotPassword.jsp";
  		
      	RequestDispatcher rd = request.getRequestDispatcher(destination);
      	request.setAttribute("infoMessage", infoMessage);
      	rd.forward(request, response);
	}
}
