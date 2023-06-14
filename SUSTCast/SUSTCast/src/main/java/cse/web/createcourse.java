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
 * Servlet implementation class createcourse
 */
@WebServlet("/createcourse")
public class createcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createcourse() {
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
	
		// Creating a new course
        Connection connection;
        HttpSession session = request.getSession();
        try {
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM login WHERE email='"+request.getParameter("teacher")+"';");
            ResultSet resultSet = statement.executeQuery(query);
            int count=0;
            while (resultSet.next()) {
                count++;
                if(resultSet.getString(3).equals("1")){
                    String name=resultSet.getString(4);
                    String role=resultSet.getString(5);
                    Statement statement1 = connection.createStatement();
                    String query1 = String.format("SELECT * FROM course");
                    ResultSet resultSet1 = statement1.executeQuery(query1);
                    int a[]=new int[10000];
                    for(int i=0;i<a.length;i++) {
                        a[i]=100000+i;
                    }
                    while (resultSet1.next()) {
                        int cur=Integer.parseInt(resultSet1.getString(1));  
                        System.out.println(cur);
                        a[cur-100000]=-1;
                    }
                    

                    Random rand = new Random(); 
                    int code = rand.nextInt(10000); 
                    while(a[code]==-1) {
                    	code=(code+1)%10000;
                    }
                    code=a[code];
                    Statement statement2 = connection.createStatement();
                    String query2 = String.format("INSERT INTO course (code,title,decs,teacher,teacher_name,role,sub) VALUES ('"+code+"','"+request.getParameter("title")+"','"+request.getParameter("decs")+"','"+request.getParameter("teacher")+"','"+name+"','"+role+"','"+request.getParameter("sub")+"');");
                    System.out.println(query2);
                    statement2.execute(query2); 
                    session=MySession.LoadSession(connection,session);
                    session.setAttribute("verdict1","Valid Email");
                    RequestDispatcher view=request.getRequestDispatcher("course.jsp");
		    		view.forward(request,response);
               }else {
                    session.setAttribute("verdict1","Invalid Email");
                    response.sendRedirect(request.getContextPath() + "/course.jsp");    
                }
            }
            if(count==0) {
                session.setAttribute("verdict1","Invalid Email");
                response.sendRedirect(request.getContextPath() + "/course.jsp");            
            }
            connection.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
    
		
	}

}
