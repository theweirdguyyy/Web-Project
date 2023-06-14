package cse.web;
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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class MySession{

    public static HttpSession LoadSession(Connection con, HttpSession session){
    	session.setAttribute("coursetitle",null );
		session.setAttribute("cname",null );
		session.setAttribute("cemail",null );
    	Statement statement2;
		try {
			
			statement2 = con.createStatement();
			String query2 = String.format("SELECT * FROM course");
			ResultSet resultSet2 = statement2.executeQuery(query2);
			List<String> code=new ArrayList<String>(); 
			List<String> title=new ArrayList<String>(); 
			List<String> decs=new ArrayList<String>(); 
			List<String> name=new ArrayList<String>(); 
			List<String> role=new ArrayList<String>(); 
			List<String> sub=new ArrayList<String>(); 
			while(resultSet2.next()) {
				
				code.add(resultSet2.getString(1));
				title.add(resultSet2.getString(2));
				decs.add(resultSet2.getString(3));
				name.add(resultSet2.getString(5));
				role.add(resultSet2.getString(6));
				 sub.add(resultSet2.getString(7));
				
				
			}
			session.setAttribute("code",code );
			session.setAttribute("title",title );
			session.setAttribute("decs",decs );
			session.setAttribute("name",name );
			session.setAttribute("role",role );
			session.setAttribute("sub",sub );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
        
    }
    public static HttpSession LoadSessionTeacher(Connection con, HttpSession session,String mail){
    	session.setAttribute("coursetitle",null );
		session.setAttribute("cname",null );
		session.setAttribute("cemail",null );
    	Statement statement2;
		try {
			statement2 = con.createStatement();
			String query2 = String.format("SELECT * FROM course WHERE teacher='"+mail+"';");
			ResultSet resultSet2 = statement2.executeQuery(query2);
			List<String> code=new ArrayList<String>(); 
			List<String> title=new ArrayList<String>(); 
			List<String> decs=new ArrayList<String>(); 
			List<String> name=new ArrayList<String>(); 
			List<String> role=new ArrayList<String>(); 
			List<String> sub=new ArrayList<String>(); 
			while(resultSet2.next()) {
				
				code.add(resultSet2.getString(1));
				title.add(resultSet2.getString(2));
				decs.add(resultSet2.getString(3));
				name.add(resultSet2.getString(5));
				role.add(resultSet2.getString(6));
				 sub.add(resultSet2.getString(7));
				
				
			}
			session.setAttribute("code",code );
			session.setAttribute("title",title );
			session.setAttribute("decs",decs );
			session.setAttribute("name",name );
			session.setAttribute("role",role );
			session.setAttribute("sub",sub );
			session.setAttribute("student","teacher" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
        
    }
    public static HttpSession LoadSessionStudent(Connection con, HttpSession session,String mail){
    	session.setAttribute("coursetitle",null );
		session.setAttribute("cname",null );
		session.setAttribute("cemail",null );
    	Statement statement2;
		try {
			statement2 = con.createStatement();
			
			String query2 = String.format("SELECT * FROM student WHERE email='"+mail+"';");
			ResultSet resultSet2 = statement2.executeQuery(query2);
			List<String> code=new ArrayList<String>(); 
			List<String> title=new ArrayList<String>(); 
			List<String> decs=new ArrayList<String>(); 
			List<String> name=new ArrayList<String>(); 
			List<String> role=new ArrayList<String>(); 
			List<String> sub=new ArrayList<String>(); 
			while(resultSet2.next()) {
				System.out.println(resultSet2.getString(1));
				Statement statement3 = con.createStatement();
				String query3 = String.format("SELECT * FROM course WHERE code='"+resultSet2.getString(1)+"';");
				ResultSet resultSet3 = statement3.executeQuery(query3);
				while(resultSet3.next()) {
					code.add(resultSet3.getString(1));
					title.add(resultSet3.getString(2));
					decs.add(resultSet3.getString(3));
					name.add(resultSet3.getString(5));
					role.add(resultSet3.getString(6));
					 sub.add(resultSet3.getString(7));
				}
			}
			
			session.setAttribute("code",code );
			session.setAttribute("title",title );
			session.setAttribute("decs",decs );
			session.setAttribute("name",name );
			session.setAttribute("role",role );
			session.setAttribute("sub",sub );
			session.setAttribute("student","student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
        
    }
    public static HttpSession LoadSessionInfo(Connection con, HttpSession session,String classcode){
    	session.setAttribute("coursetitle",null );
		session.setAttribute("cname",null );
		session.setAttribute("cemail",null );
    	Statement statement2;
    	String title="";
		try {
			
			Statement statement = con.createStatement();
			String query = String.format("SELECT * FROM course WHERE code='"+classcode+"';");
			System.out.println(classcode);
			//System.out.println("fuck ");
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				title=resultSet.getString(2);
			}
			session.setAttribute("coursetitle",title );
			statement2 = con.createStatement();
			String query2 = String.format("SELECT * FROM student WHERE code='"+classcode+"';");
			ResultSet resultSet2 = statement2.executeQuery(query2);
			List<String> name=new ArrayList<String>(); 
			List<String> email=new ArrayList<String>(); 
			
			while(resultSet2.next()) {
				email.add(resultSet2.getString(2));
				System.out.println(resultSet2.getString(2));
				Statement statement3 = con.createStatement();
				String query3 = String.format("SELECT * FROM login WHERE email='"+resultSet2.getString(2)+"';");
				ResultSet resultSet3 = statement3.executeQuery(query3);
				while(resultSet3.next()) {
					System.out.println(resultSet3.getString(4));
					name.add(resultSet3.getString(4));
					
				}
				
				session.setAttribute("cname",name );
				session.setAttribute("cemail",email );
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
        
    }
}