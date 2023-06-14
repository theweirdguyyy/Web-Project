<!DOCTYPE html>
<html>
<head>
	<title>Student Information</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
		}
		h1 {
			color: #333333;
			text-align: center;
		}
		.card {
			margin: 20px;
			padding: 10px;
			background-color: #ffffff;
			box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
			border-radius: 5px;
			font-size: 18px;
			font-weight: bold;
			display:flex;
			flex-direction: row;
  			align-items: center;
  			justify-content: center;
		}
		.name {
			color: #3366cc;
			
			
		}
		.email {
			color: #cc3366;
			margin-left:20%;
		}
	</style>
</head>
<body>
	 <%@ page language="java" import="java.util.*,java.lang.*" %>
    <% 
    	 List<String> name = ( List<String> ) session.getAttribute("cname") ;
    	List<String> email = ( List<String> ) session.getAttribute("cemail") ;
    		   String title= (String)session.getAttribute("coursetitle") ;
    		   int sz=0;
    		   if( ( List<String> ) session.getAttribute("cname")!=null){
    			   sz=name.size();
    		   }
    	
    	
    %>
    
	<h1> <% out.println(title);%> </h1>
	 <% for(int i=0;i<sz;i++) {%>
	<div class="card">
		<div style="width:400px;display:flex;flex-direction: row;align-items: center;"><span class="name">Name:</span><%out.println(name.get(i));%></div>
		<span class="email">Email:</span> <% out.println(email.get(i));%>
	</div>
	<%} %>
</body>
</html>
