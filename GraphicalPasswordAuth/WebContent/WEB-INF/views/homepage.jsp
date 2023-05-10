<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE HTML>
<html>
<head>

</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<ol>
  <li>${user}</li>
  <li><a href="javascript:document.getElementById('logout').submit()">Logout</a></li>

</ol>

<h3>Welcome to  Graphic Password Authentication System</h3>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
</form>

	
	
</body>
</html>

