<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" +http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Crunchify.com JSP Servlet Example </title>
</head>
<body>
<div align='center'>
Username: <%= request.getParameter("username") %> <br> Password: <%=
request.getParameter("password") %>
</div>
</body>
</html>