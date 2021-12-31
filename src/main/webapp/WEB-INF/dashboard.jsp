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
<title>Dashboard</title>
</head>
<body>

	<h1>Welcome,<c:out value="${user.firstName}"/></h1>
	
	<div class="row">
		<div class="col">
			<h2>Ideas</h2>
		</div>
		<div class="col">
			<a href="/dashboard/sortbylikesasc">Low Likes</a>
			<a href="/dashboard/sortbylikesdesc">High Likes</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	<table class="table table-dark" style="width: 80%;">
		<thead>
			<tr>
				<th>Idea</th>
				<th>Created By</th>
				<th>Likes</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ideas}" var="idea">
				<tr>
					<td>
					<a href="/ideas/idea/${idea.id}"> <c:out value="${idea.ideaName}" /></a>
					</td>
					<td><c:out value="${idea.user.firstName}" /></td>
					<td><c:out value="${idea.likers.size()}" /></td>
					
					<td>
				 	<c:choose>
					<c:when test="${idea.likers.contains(user)}">
							<a href="/ideas/unlike/${idea.id}">unlike</a>
					</c:when>
					<c:otherwise>
							<a href="/ideas/like/${idea.id}">Like</a>
					</c:otherwise>
					</c:choose>		
            		</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/ideas/new">Create an idea</a>

</body>
</html>