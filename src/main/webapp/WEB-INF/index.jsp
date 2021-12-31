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
<title>Registration and Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			<h1>Registration</h1>
			<form:form method="POST" action="/registration" modelAttribute="user">
			<div class="form-group">
		        <form:label path="firstName">First Name:</form:label>
		        <form:errors class="text-danger" path="firstName"/>
		        <form:input class="form-control" path="firstName"/>
		    </div>
		    <div class="form-group">
		        <form:label path="lastName">Last Name:</form:label>
		        <form:errors class="text-danger" path="lastName"/>
		         <form:input class="form-control" path="lastName"/>
		    
		    </div>
		    <div class="form-group">
		        <form:label path="email">Email:</form:label>
		        <form:errors class="text-danger" path="email"/>
		        <form:input class="form-control" path="email"/>
		    </div>
	      	<div class="form-group">
	            <form:label path="password">Password:</form:label>
	            <form:password class="form-control" path="password"/>
	            <form:errors class="text-danger" path="password"/>
	        </div>
	         <div class="form-group">
	            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password class="form-control" path="passwordConfirmation"/>
	            <form:errors class="text-danger" path="passwordConfirmation"/>
	        </div>
	        <input type="submit" value="Register!"/>
			</form:form>
			</div>
			
			<div class="col">
			<h1>Login</h1>
    		<form method="post" action="/login">
    		<p class="text-danger">
    		<c:out value="${error}"/>
    		</p>
        	<div class="form-group">
            	<label type="email" for="email">Email</label>
            	<input class="form-control"  type="text" id="email" name="email"/>
        	</div>
        	<div class="form-group">
            	<label for="password">Password</label>
            	<input class="form-control"  type="password" id="password" name="password"/>
        	</div>
        		<input type="submit" value="Login!"/>
    		</form>  
			</div>
			
		</div>
	</div>
</body>
</html>