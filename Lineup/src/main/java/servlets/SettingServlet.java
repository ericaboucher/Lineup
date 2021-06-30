package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.ApplicationDao;

@WebServlet("/settingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 154545L;
	public User user;
	public String infoMessage = null;

    public SettingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/setting.html");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//collect data from form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
			String newEmail = request.getParameter("email");
			String phoneNum = request.getParameter("phoneNum");
			String password = request.getParameter("password");
			String oldEmail = user.getEmail();
			
			ApplicationDao dao = new ApplicationDao();
			
			int rows = dao.updateUser(firstName, lastName, newEmail, phoneNum, password, oldEmail);
				if(rows == 0) {
					infoMessage = "Sorry, an error occurred.";
				} else {
					infoMessage = "User account updated successfully!" ;
				}

				//write the message back to user
				String page = getHTMLString(request.getServletContext().getRealPath("/setting.html"), infoMessage);
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
