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
            request.getRequestDispatcher("/home.html").forward(request, response);
        } else {
            String errorMessage = "Sorry, email or password is not valid. Please try again.";
            //write the message back to user
            String page = getHTMLString(request.getServletContext().getRealPath("/index.html"), errorMessage);
            response.getWriter().write(page);
        }
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
