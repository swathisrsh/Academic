<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmBinding() {
		var error = document.getElementById("error");
		error.innerHTML = "<span style='color: red;'>"
				+ "Account Locked. Please wait for sometime to unlock account or contact Administrator</span>"
	}
</script>
</head>
<body onload="confirmBinding();">
	<span id="error"></span>
</body>

</html>