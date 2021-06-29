package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Guardian;
import beans.User;
import dao.ApplicationDao;

@WebServlet ("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public User guardian;
	public String infoMessage = null;
	
    public RegistrationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect data from form
		String userType = request.getParameter("usertype");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String phoneNumber = request.getParameter("phone");

		if (userType == "Guardian") {
			guardian = new Guardian(email, password, firstName, lastName, phoneNumber);
		}else {
			//create Staff user
			System.out.println("Error. No way to create staff.");
		}

		//save user to db
		ApplicationDao dao = new ApplicationDao();
		int rows = dao.createUser(guardian);

		if(rows == 0) {
			infoMessage = "Sorry, an error occurred.";
		} else {
			infoMessage = "User created successfully!";
		}

		//write the message back to user
		String page = getHTMLString(request.getServletContext().getRealPath("/register.html"), infoMessage);
		response.getWriter().write(page);
	}

	public String getHTMLString(String filePath, String message) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}

		reader.close();
		String page = buffer.toString();

		page = MessageFormat.format(page, message);

		return page;		
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	// TODO Auto-generated method stub
//}

}