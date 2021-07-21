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

import dao.UserDao;

@WebServlet("/settingServlet")
public class SettingServlet extends HttpServlet {
<<<<<<< Upstream, based on branch 'CapstoneProject' of https://github.com/ericaboucher/Lineup.git
    private static final long serialVersionUID = 154545L;
    public String infoMessage = null;
=======
	private static final long serialVersionUID = 154545L;
	//public User user;
	public String infoMessage = null;
>>>>>>> dbb2993 testing

    public SettingServlet() {
        super();
        System.out.println("Setting Servlet Created!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached the GET Setting Servlet");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        UserDao dao = new UserDao();
        boolean isValidUser = dao.validateUser(email, password);

<<<<<<< Upstream, based on branch 'CapstoneProject' of https://github.com/ericaboucher/Lineup.git
        if (isValidUser) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/setting.html");
        dispatcher.include(request, response);
        doPost(request, response);
        }
    }
=======
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get User from the Context
	    User currentUser = (User)request.getServletContext().getAttribute("user");
	    
		//collect data from form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
			String newEmail = request.getParameter("email");
			String phoneNum = request.getParameter("phoneNum");
			String password = request.getParameter("password");
			
			ApplicationDao dao = new ApplicationDao();
			//HttpSession session = request.getSession();
			//String oldEmail = (String) session.getAttribute("email");
			
			//user = dao.readUser(oldEmail);
			currentUser.editEmail(newEmail);
			currentUser.editPassword(password);
			currentUser.editFirstName(firstName);
			currentUser.editLastName(lastName);
			currentUser.editPhoneNumber(phoneNum);
			int rows = dao.updateUser(currentUser);
				if(rows == 0) {
					infoMessage = "Sorry, an error occurred.";
				} else {
					infoMessage = "User account updated successfully!" ;
				}
>>>>>>> dbb2993 testing

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached the POST Setting Servlet!");
        // Get User from the Session
        HttpSession session = request.getSession();
        String oldEmail = (String) session.getAttribute("email");

        //collect data from form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String newEmail = request.getParameter("newEmail");
        String phoneNum = request.getParameter("phoneNum");
        String newPassword = request.getParameter("newPassword");

        //create new user dao and update the user
        UserDao userDao = new UserDao();
        int rows = userDao.updateUser(firstName, lastName, newEmail, phoneNum, newPassword, oldEmail);
        
        if(rows == 0) {
            infoMessage = "Sorry, an update error occurred.";
        } else {
            infoMessage = "User account updated successfully!" ;
        }
        System.out.println(infoMessage);
        //write the message back to user
        String page = getHTMLString(request.getServletContext().getRealPath("/setting.html"), infoMessage);
        response.getWriter().write(page);
    }

    public String getHTMLString(String filePath, String message) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line="";
        StringBuffer buffer = new StringBuffer();
        while((line=reader.readLine())!=null) {
            buffer.append(line);
        }

        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, message);
        return page;		
    }
}
