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

import beans.Guardian;
import beans.Hash;
import beans.Staff;
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

        Hash h = new Hash(password);
        password = h.encrypt(password);
        
        if (userType.equals("Guardian")) {
            //create user of type guardian
            user = new Guardian(email, password, firstName, lastName, phoneNum);
            UserDao.createUser(user);
            infoMessage = "User created successfully! Verification email sent to " + user.getEmail();
        } else if (userType.equals("Staff")) {
            //create Staff user
            user = new Staff(email, password, firstName, lastName, phoneNum);
            UserDao.createUser(user);
            infoMessage = "Staff user created successfully! Verification email sent to " + user.getEmail();
        } else {
            // invalid user type
            infoMessage = "Error. Invalid user type.";
        }

        //write the message back to user
      	String destination = "/register.jsp";
      		
      	RequestDispatcher rd = request.getRequestDispatcher(destination);
      	request.setAttribute("infoMessage", infoMessage);
      	rd.forward(request, response);
      		
        //write the message back to user
        //String page = getHTMLString(request.getServletContext().getRealPath("/register.html"), infoMessage);
        //response.getWriter().write(page);
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