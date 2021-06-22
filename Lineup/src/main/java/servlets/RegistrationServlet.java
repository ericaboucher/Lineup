package servlets;
<<<<<<< HEAD

=======
>>>>>>> branch 'CapstoneProject' of https://github.com/ericaboucher/Lineup.git
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
@WebServlet("/Registration")
=======
@WebServlet("/RegistrationServlet")
>>>>>>> branch 'CapstoneProject' of https://github.com/ericaboucher/Lineup.git
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
<<<<<<< HEAD
        // TODO Auto-generated constructor stub
=======
        super();
>>>>>>> branch 'CapstoneProject' of https://github.com/ericaboucher/Lineup.git
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = request.getParameter("usertype");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String phoneNumber = request.getParameter("phone");
	}
}
