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
    private static final long serialVersionUID = 154545L;
    public String infoMessage = null;

    public SettingServlet() {
        super();
        System.out.println("Setting Servlet Created!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached the GET Setting Servlet");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        boolean isValidUser = UserDao.validateUser(email, password);

        if (isValidUser) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/setting.html");
            dispatcher.include(request, response);
            doPost(request, response);
        }
    }

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
        int rows = UserDao.updateUser(firstName, lastName, newEmail, phoneNum, newPassword, oldEmail);

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