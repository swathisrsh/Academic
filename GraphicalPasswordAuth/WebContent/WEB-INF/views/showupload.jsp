<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>File Uploaded</h1>
	<form action="<c:url value='/completeRegistration' />" method='POST'>
		<input type="submit" value="Next" /> 
		<input type="hidden"
			name="userName" value="${userName}"> 
			<input type="hidden"
			name="email" value="${email}"> 
			<input type="hidden"
			name="password" value="${password}">
			<input type="hidden"
			name="imageSource" value="${imgName}">
			<input type="hidden"
			name="imageData" value="${imgContent}">
	</form>
</body>
</html>