package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 15642L;
    public String infoMessage = null;

    public DeleteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        int rows = UserDao.deleteUser(email);
        if(rows == 0) {
            infoMessage = "Sorry, a delete error occurred. User: " + email;
        } else {
            infoMessage = "User account deleted successfully!" ;
        }
        System.out.println(infoMessage);
        //write the message back to user       
        String destination = "/setting.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        request.setAttribute("infoMessage", infoMessage);
        rd.forward(request, response);
        
    }

}
