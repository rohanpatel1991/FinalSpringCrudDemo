<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <h2 align="center">Employee List </h2>
      <a href="EmpEntry">Add New Employee</a>&nbsp;&nbsp;&nbsp;
      <a href="index.jsp">Home</a>
     
      <table border="1px" style="margin-top: 10px;">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Salary</th>
				<th>Designation</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
            <c:forEach var="emp" items="${list}">	
				<tr>
					<td>${emp.id}</td>
					<td>${emp.name}</td>
					<td>${emp.salary}</td>
					<td>${emp.designation}</td>
					<td><a href="update/${emp.id}">Edit</a></td>
					<td><a href="delete/${emp.id}" 
					onclick="return confirm('Do you want to Delete?')">
					Delete</a>
					</td>
				</tr>
            </c:forEach>
</table>
</body>
</html>