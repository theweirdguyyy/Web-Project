package cse.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Servlet implementation class joincourse
 */
@WebServlet("/joincourse")
public class joincourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joincourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view=request.getRequestDispatcher("dashbord.jsp");
		view.forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get form data
        String code = request.getParameter("mycode");
		HttpSession session = request.getSession();
        String email=(String)session.getAttribute("current_u");
        try {
        	Connection connection = Database.getConnection();
        	Statement statement = connection.createStatement();
        	//String query = String.format("DELETE FROM student;");
        	String query = String.format("INSERT INTO student (code,email) VALUES ('"+code+"','"+email+"');");
        	statement.execute(query);
        	session=MySession.LoadSessionStudent(connection,session,email);
        	connection.close();

        } catch (SQLException e) {

        }
		doGet(request, response);
	}

}
