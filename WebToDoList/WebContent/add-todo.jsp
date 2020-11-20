<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a todo</title>
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
				<form action="AddToDoServlet" method="post"> 
					<table>
				        <tr>
				            <td><h2>Add New Todo: </h2></td>
				        </tr>
	    			</table>
					<table>
				        <tr>
				            <td><h3>Description: </h3></td>
				            <td> <input type="text" name="description" /> </td>
				        </tr>
	    			</table>
	    			
	    			<table>
	    				<tr>
							<th>First Name </th>
							<th>Last Name</th>
							<th>Add Student</th>
						</tr>
		    			<c:forEach var="users" items="${LIST_USERS }" > 
							<tr>
								<td> ${users.first_Name}</td> 
								<td> ${users.last_Name}</td> 
								<td> <input type="checkbox" name="selected" value="${users.id}"/> </td>
							</tr>
						</c:forEach>
					</table>
					
					<table>
						<tr>
					        <td> <input class ="loginpagetext" type="submit" name="SaveButton" value="Save" id = "ButtonSave"/> </td>
					    </tr>
					    <tr>
					    	<td> <a href="ToDoServlet"> Back to main page</a></td>
					    </tr>
				    </table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>