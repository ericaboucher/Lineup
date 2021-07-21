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
import dao.UserDao;

@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 17871L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
        dispatcher.include(request, response);

        //collect data from login form 
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //call dao to validate user
        UserDao dao = new UserDao();
        boolean isValidUser = dao.validateUser(email, password); 

        //check to see if user is valid
        if(isValidUser) {
            //set up HTTP session
            HttpSession session = request.getSession();
            //set username as attribute
            User currentUser = dao.readUser(email);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("currentUser", currentUser);
            System.out.println("User " + email + " is logged in");
            //context.setAttribute("user", currentUser);
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
