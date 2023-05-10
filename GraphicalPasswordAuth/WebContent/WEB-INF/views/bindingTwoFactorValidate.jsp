<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function confirmBinding() {
    var googleRegex =/\d{6}/;
    var inputGoogleCode =  window.prompt("Please enter 6 digits google Verification Code");
    document.getElementById("twoFactorCode").value = inputGoogleCode;
    var secretkey = document.getElementById("randomSecretKey").value;
}

</script>
</head>
<body>
<form id="finalloginpage" action="<c:url value='/bindingGoogleTwoFactorValidate'/>" method='POST'>
<div>
Please enter 6 digits Verification Code
<input type="password" id = "twoFactorCode" name="twoFactorCode" >
<input type="hidden" id = "randomSecretKey" name="randomKey" value="${randomSecretKey}">
<input type="submit" value="Next"/>
</div>
</form>
<%-- <center>
<form action="<c:url value='/bindingGoogleTwoFactorValidate' />" method='POST' onsubmit="confirmBinding()">
	<img src="${qrCodeImageBase64}" style="width: 150px"/>
	<input type="hidden" id = "twoFactorCode" name="twoFactorCode" >
	<input type="hidden" id = "randomSecretKey" name="randomKey" value="${randomSecretKey}">
	<input type="submit" value="Next"/>
</form>
	</center> --%>
</body>
</html>