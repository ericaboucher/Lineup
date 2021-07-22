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
        String page = getHTMLString(request.getServletContext().getRealPath("/forgotPassword.html"), infoMessage);
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
}
