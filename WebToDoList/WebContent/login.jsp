<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>
<body>
	<form action="LoginServlet" method="post">
		<table id="logintable">
			<td><img src = "logo_esilv_png_couleur.png" width="150" height="150"/> </td>
			<td><h3>Bienvenue sur le ToDo portail de l'ESILV</h3> </td>
			<tr>
				<td class ="loginpagetext">Username: </td>
				<td><input class ="loginpagetext" type="text" name="username" value="${username }"></td>
			</tr>	
			<tr>
				<td class ="loginpagetext">Password: </td>
				<td><input class ="loginpagetext" type="password" name="password" value="${password }"></td>
			</tr>	
			<tr>
				<td ><input class ="loginpagetext" type="submit" value="Login"></td>
				<td><a href= "register.jsp">Registration</a></td>
			</tr>
		</table>
	</form>
</body>
</html>