
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Hash;
import beans.User;
import dao.UserDao;

@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 17871L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //collect data from login form 
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //encrypt password
        Hash h = new Hash(password);
        password = h.encrypt(password);
        
        //call dao to validate user
        boolean isValidUser = UserDao.validateUser(email, password); 
        //set up HTTP session
        HttpSession session = request.getSession();

        //check to see if user is valid
        if(isValidUser) {
            //set username as attribute
            User currentUser = UserDao.readUser(email);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("currentUser", currentUser);
            System.out.println("User " + email + " is logged in");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
        	//write the message back to user
            String errorMessage = "Sorry, email or password is not valid. Please try again.";
            String destination = "/index.jsp";           
            RequestDispatcher rd = request.getRequestDispatcher(destination);
            request.setAttribute("errorMessage", errorMessage);
            rd.forward(request, response);
        }
        
    }

}
