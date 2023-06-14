<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
 <script src="assets\js\cources.js"></script>
    <title>LearnEase</title>
    <link rel="stylesheet" href="assets\css\courses.css" type="text/css">
</head>
<body >
	

	 <p>
	 <%@ page language="java" import="java.util.*,java.lang.*" %>
    <% 
    	List<String> list = ( List<String> ) session.getAttribute("old_email_login") ;
    	List<String> code = ( List<String> ) session.getAttribute("code") ;
    	List<String> title = ( List<String> ) session.getAttribute("title") ;
    	List<String> decs = ( List<String> ) session.getAttribute("decs") ;
    	List<String> name = ( List<String> ) session.getAttribute("name") ;
    	List<String> role = ( List<String> ) session.getAttribute("role") ;
    	List<String> sub = ( List<String> ) session.getAttribute("sub") ;
    %>
	</p>
	
    <div class = "nav" >
        <i class="fa-solid fa-check" style="color: #89CFF0; margin-left: 35px;">
        <a href="" class="logo" style="cursor:text;  font-size:30px;font-family: 'Dancing Script', cursive;text-decoration: none;
            font-family: 'Island Moments', cursive;
            font-family: 'Kalam', cursive;
            font-family: 'Marhey', cursive;"><span style="color: #89CFF0;">SUST</span>Cast</a></i>
            <div data-toggle="modal" data-target="#myModal" style="margin-left: 70%;" class="abs">+</div>
            <div style="margin-left: 1%;"  class="nav_hover"><a  style=" cursor:pointer;margin:10px;"><%out.println((String)session.getAttribute("name1")); %></a></div>
            <div onclick="logout()" class="nav_hover"><a style="cursor:pointer; margin:10px;">Log out</a></div>
    </div>
    
    
    <% for(int i=0;i<code.size();i=i+4) {%>
    <div id="cources" style="margin:20px;display:flex;flex-direction: row;">
    	<% for(int j=i;j<Math.min(i+4, code.size());j++) { %>
        <div class="card" onClick="fun(<%out.println(code.get(j));%>)">

            <img src=<%out.println("\""+sub.get(j)+".jpg"+"\"");%> alt="Avatar">
            <div class="container">
                <h3><b><%out.println(title.get(j));%></b></h3>
                <h6><%out.println(name.get(j));%>, <%out.println(role.get(j));%></h6>
                <h6>Department:<%out.println(sub.get(j));%> Course code:<%out.println(code.get(j));%></h6>
                <p><%out.println(decs.get(j));%> </p>
            </div>

        </div>
        <% } %>
    </div>
	<% } %>
	
	
	
<div class="container">
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    

      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Create Course</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" style="display:flex;flex-direction: column;align-items: center;justify-content: center;">
          <form action="<%=request.getContextPath()%>/createcourse" style="width:90%;Height:90%;display:flex;flex-direction: column;align-items: center;justify-content: center;" method="post"> 
          <input id="title" type="text"  name="title" style="margin: 1%;" class="form-control" placeholder="Enter Course Title" required>
		  <input id="decs" type="text"  name="decs" style="margin: 1%;" class="form-control" placeholder="Enter Course Description" required>
		  <input id="teacher" type="text"  name="teacher" style="margin: 1%;" class="form-control" placeholder="Assign Course Teacher " required>
		  <div style="margin: 1%;" ><label for="subs">Choose a Department :</label>
		  <select id="sub" name="sub">
 			 <option value="Physics">Physics</option>
  			<option value="Chemistry">Chemistry</option>
  			<option value="Math">Math</option>
  			<option value="Biology" >Biology</option>
  			<option value="Others" selected>Others</option>
		</select>
		<div style="display:flex;flex-direction: column;align-items: center;justify-content: center;color:red;">
			<%
				if(session.getAttribute("verdict1")!=null){
					if(session.getAttribute("verdict1").equals("Valid Email")){
						
					}else{
						out.println(session.getAttribute("verdict1"));  
					}
				}
				

			%>
</div>
		</div>
		<button type="submit" class="form-control btn btn-primary rounded submit px-3">Create Course</button>
		</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
<script >
function fun(x) {
  	console.log(x);
  	window.open("http://localhost:8080/SUSTCast/student?classcode="+x,"_self");
}
function logout() {
  	console.log("hii");
	document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
	sessionStorage.clear()
	localStorage.clear()
	caches.keys().then(keys => {
  	keys.forEach(key => caches.delete(key))
	});
	window.open("http://localhost:8080/SUSTCast/logout","_self");
	
}
</script>
</body>
</html>
