<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Idea Detail</title>
</head>
<body>
	
	<h1><c:out value="${idea.ideaName}" /></h1>
	
	<h4>Created By: <c:out value="${idea.user.firstName}"/></h4>

	<h4>Content: <c:out value="${idea.ideaContent}"/></h4>

	<h4>Users who liked this idea</h4>
	
	<table class="table table-dark" style="width: 80%;">
	<thead>
		<tr>
			<th>Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${idea.likers}" var="liker">
		<tr><c:out value="${liker.firstName}" /></tr>
		</c:forEach>
	</tbody>
	</table>
	
	<a href="/dashboard">Dashboard</a>

	<!-- offer edit function only if the idea belongs to the logged in user -->
	<c:if test="${idea.user.id==userLoggedIn}">
		<a href="/ideas/edit/${idea.id}">Edit</a>
	</c:if>
</body>
</html>