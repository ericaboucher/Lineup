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
import javax.servlet.http.HttpSession;
import dao.UserDao;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 15642L;
    //public User user;
    public String infoMessage = null;

    public DeleteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //UserDao userDao = new UserDao();
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
        String page = getHTMLString(request.getServletContext().getRealPath("/setting.html"), infoMessage);
        response.getWriter().write(page);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ApplicationDao dao = new ApplicationDao();
//        //HttpSession session = request.getSession();
//        //String email = (String) session.getAttribute("email");
//        //ervletContext context = request.getServletContext();
//        User currentUser = (User) request.getServletContext().getAttribute("user");
//        //user = dao.readUser(currentUseremail);
//        
//        int rows = dao.deleteUser(currentUser.getEmail());
//        if(rows == 0) {
//            infoMessage = "Sorry, an error occurred.";
//        } else {
//            infoMessage = "User account deleted successfully!" ;
//        }
//        //write the message back to user
//        String page = getHTMLString(request.getServletContext().getRealPath("/setting.html"), infoMessage);
//        response.getWriter().write(page);
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
