package cse.web;

import java.util.*;

  



import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LearnEase
 */
@WebServlet("/SUSTCast")
public class SUSTCast extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SUSTCast() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// Log in 
				Connection connection;
				HttpSession session = request.getSession();
				List<String> list=new ArrayList<String>();   
				list.add("Mango");  
				list.add("Apple");  
				list.add("Banana");  
				list.add("Grapes");  
		        session.setAttribute("old_email_login",list );
				try {
					connection = Database.getConnection();
					// Get database connection
			        Statement statement = connection.createStatement();
			        String query = String.format("SELECT * FROM login WHERE email='"+request.getParameter("mail")+"';");
			        ResultSet resultSet = statement.executeQuery(query);
			        int count=0;
			        while (resultSet.next()) {
			        	count++;
			        	// Set Session
			        	if(resultSet.getString(2).equals(request.getParameter("password"))) {
			        		session.setAttribute("current_u",request.getParameter("mail"));
			        		session.setAttribute("name1",resultSet.getString(4));
			        		if(resultSet.getString(3).equals("2")) {
			        			session=MySession.LoadSession(connection,session);
			        			RequestDispatcher view=request.getRequestDispatcher("course.jsp");
					    		view.forward(request,response);	
			        		}else if(resultSet.getString(3).equals("1")){
			        			session=MySession.LoadSessionTeacher(connection,session,request.getParameter("mail"));
			        			RequestDispatcher view=request.getRequestDispatcher("dashbord.jsp");
					    		view.forward(request,response);	
			        		}else {
			        			session=MySession.LoadSessionStudent(connection,session,request.getParameter("mail"));
			        			RequestDispatcher view=request.getRequestDispatcher("dashbord.jsp");
					    		view.forward(request,response);	
			        		}
			        		
			        	}else {
			        		session.setAttribute("verdict","Incorrect Password");
				    		RequestDispatcher view=request.getRequestDispatcher("index.jsp");
				    		view.forward(request,response);	
			        	}
			        }
			        if(count==0) {
			        	session.setAttribute("verdict","Email is not Registered");
			    		RequestDispatcher view=request.getRequestDispatcher("index.jsp");
			    		view.forward(request,response);	        	
			        }
			        // Close the connection
			        connection.close();
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
		            // Use the connection to execute SQL statements
	}

}
