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
<title>Edit Idea</title>
</head>
<body>
<div class="container">
		<h1>Edit - ${ideaToEdit.ideaName}</h1>

		<form:form action="/ideas/update/${ideaToEdit.id}" method="post" modelAttribute="editedIdea">
		<div class="form-group">
	        <form:label path="ideaName">Title:</form:label>
	        <form:errors class="text-danger" path="ideaName"/>
	        <form:input class="form-control" path="ideaName"/>

			<form:label path="ideaContent">Content:</form:label>
	        <form:errors class="text-danger" path="ideaContent"/>
	        <form:input class="form-control" path="ideaContent"/>

	    </div>
	    <input type="submit" class="btn btn-primary" value="Submit"/>
		</form:form>
		
		<a href="/ideas/delete/${ideaToEdit.id}">Delete</a>
		
	</div>
</body>
</html>