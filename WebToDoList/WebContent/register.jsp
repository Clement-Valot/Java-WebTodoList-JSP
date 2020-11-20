<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>
<body>
	<div id="adddiv"> 
		<form action="RegisterServlet" method="post">
			<table>
				<tr>
			    	<td><img src = "logo_esilv_png_couleur.png" width="150" height="150"/> </td>
					<td><h3>Register Page</h3> </td>
				</tr>
				<tr>
					<td class ="loginpagetext">Username: </td>
					<td><input class ="loginpagetext" type="text" name="username"></td>
				</tr>	
				<tr>
					<td class ="loginpagetext">Name: </td>
					<td><input  class ="loginpagetext" type="text" name="name"></td>
				</tr>	
				<tr>
					<td class ="loginpagetext">Surname: </td>
					<td><input  class ="loginpagetext"type="text" name="surname"></td>
				</tr>	
				<tr>
					<td class ="loginpagetext">Password: </td>
					<td><input class ="loginpagetext"type="password" name="password"></td>
				</tr>
				<tr>
					<td class ="loginpagetext">Confirm Password: </td>
					<td><input class ="loginpagetext" type="password" name="cfpassword"></td>
				</tr>	
				<tr>
					<td><a href= "LoginServlet">Sign in</a></td>
					<td ><input class ="loginpagetext" type="submit" value="Register" name="Register"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>