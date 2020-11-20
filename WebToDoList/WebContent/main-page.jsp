<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main page</title>
<link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>
<body style="align:center">
    <form action="ToDoServlet" method="post">
        <table id="logintable">
        	<tr>
		    	<td><img src = "logo_esilv_png_couleur.png" width="150" height="150"/> </td>
				<td><h3>Bienvenue ${USER.first_Name} ${USER.last_Name} sur la page de l'ESILV</h3> </td>
			</tr>
            <c:if test="${USER.instructor == true }">
            	<tr>
					<th>Description </th>
					<th>Action</th>
				</tr>
                <c:forEach var="tempTodo" items="${TODO_LIST }" >
                    <c:url var="EditLink" value= "EditTodoServlet">
                        <c:param name="description" value="${tempTodo.description}"/>
                    </c:url>
                    <c:url var="DeleteLink" value= "DeleteTodoServlet">
                        <c:param name="description" value="${tempTodo.description}"/>
                    </c:url>
                    <tr>
                        <td> ${tempTodo.description}</td>
                        <td> <a href="${EditLink }"> Edit </a>|<a href="${DeleteLink }"> Delete</a></td>
                    </tr>
                </c:forEach>
                <td> <a href="AddToDoServlet"> Add a Todo </a></td>
                <td> <a href="LoginServlet"> Log Out </a></td>
            </c:if>
            <c:if test="${USER.instructor == false }">
            	 <table id="logintable">
	            	<tr>
						<th>Description </th>
					</tr>
	                <c:forEach var="tempTodo" items="${TODO_LIST }" >
	                <tr>
	                    <td> ${tempTodo.description}</td>
	                </tr>
	            	</c:forEach>
	            	<td> <a href="LoginServlet"> Log Out </a></td>
            	</table>
            </c:if>
        </table>
    </form>
</body>
</html>