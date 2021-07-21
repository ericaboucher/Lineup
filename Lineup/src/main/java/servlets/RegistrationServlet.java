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
import dao.UserDao;

@WebServlet (name="registrationServlet", urlPatterns={"/registrationServlet"})
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String infoMessage = null;

    public RegistrationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        //collect data from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNum = request.getParameter("phoneNum");

        if (userType.equals("Guardian")) {
            //create user of type guardian
            user = new Guardian(email, password, firstName, lastName, phoneNum);
            //UserDao dao = new UserDao();
            int rows = UserDao.createUser(user);
            if(rows == 0) {
                infoMessage = "Sorry, an error occurred.";
            } else {
                infoMessage = "User created successfully! Verification email sent to " + user.getEmail();
            }
        } else {
            //create Staff user
            infoMessage = "Error. No way to create staff.";
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
}