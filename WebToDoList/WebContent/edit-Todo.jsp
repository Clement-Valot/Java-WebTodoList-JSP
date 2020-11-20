<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit a Todo</title>
<link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>
<body>
	<div id="adddiv">
		<table>
	    	<tr>
		    	<td><img src = "logo_esilv_png_couleur.png" width="150" height="150"/> </td>
				<td><h3>Bienvenue ${USER.first_Name} ${USER.last_Name} sur la page de l'ESILV</h3> </td>
			</tr>
		</table>
		<div id="container"> 
			<div id="content">
				<form action="EditTodoServlet" method = "post">
					<table>
				        <tr>
				            <td><h2>Edit a Todo</h2></td>
				        </tr>
	    			</table>
					<table>
						<tr>
							<td><label>Description: </label> </td>
							<td><input type="text" name = "description" value="${description}"/></td>
						</tr>
						<tr>
							<td><label></label> </td>
							<td><input class ="loginpagetext" type="submit" value = "Save"/></td>
						</tr>
						<tr>
							<td><a href="ToDoServlet">Back to List</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>