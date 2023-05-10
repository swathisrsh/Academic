<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<html>
<head>
	<c:if test="${not empty error}"><div>${error}</div></c:if>
	<c:if test="${not empty message}"><div>${message}</div></c:if>
	




<title>Level1 Authentication</title>
<style>
body {
height : 50%;
background-repeat: no-repeat;
z-index: -1;
background-position: right;
background-color: #8bc34a;
background-size: 25%;
}

*[role="form"] {
	max-width: 50%;
	max-height:90%;
	padding: 15px;
	border-radius: 0.3em; 
    margin-right:4%;
    margin-top: 10%;
    margin-bottom: 15%;
    background-color: #f2f2f2;
}

*[role="form"] h2 {
	font-family: 'Open Sans', sans-serif;
	font-size: 40px;
	font-weight: 600;
	color: #000000;
	margin-top: 5%;
	text-align: center;
	text-transform: uppercase;
	letter-spacing: 4px;
	 
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.button2 {background-color: #008CBA;} /* Blue */
.button3 {background-color: #f44336;} /* Red */ 

.req {
    color: red;
}

*:required {
    background-color: gold;
}

#loginBtn {
    position: relative;
    top: 1.5em;
    text-decoration: none;
    background: #8bc34a;
    color: #fff;
    margin: auto;
    padding: 0.8em 3em;
    -webkit-box-shadow: 0px 15px 30px rgba(50, 50, 50, 0.21);
    -moz-box-shadow: 0px 15px 30px rgba(50, 50, 50, 0.21);
    box-shadow: 0px 15px 30px rgba(50, 50, 50, 0.21);
    border-radius: 25px;
    -webkit-transition: all 0.4s ease;
    -moz-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}
#loginBtn:hover {
    -webkit-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -moz-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -webkit-transition: all 0.4s ease;
    -moz-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}


</style>

<script type="text/javascript">
var colorclicked="";

function onclickgreen(){
	colorclicked = colorclicked+"Green";
	document.getElementById("pass").value = colorclicked;
}

function onclickred(){
	colorclicked = colorclicked+"Red";
	document.getElementById("pass").value = colorclicked;
}

function onclickblue(){
	colorclicked = colorclicked+"Blue";
	document.getElementById("pass").value = colorclicked;
}

</script>

</head>

<body>

	<center>

		<h3>Levell Authentication</h3>

		<br />

		<form name="next" action="<c:url value='/level1auth' />" method='POST' role="form">
			<table style="width: 20%">
				<tr>
					<td><b>UserName:</b><span class="req">*</span></td>
					<td><input type="text" name="userName" required/></td>
				</tr>
				<tr></tr>
				<tr>
					<td><b>Email:</b><span class="req">*</span></td>
					<td><input type="email" name="email" required /></td>
				</tr>
				<tr></tr>
				<tr>
					<td><b>Passphrase:<span class="req">*</span></b></td>
					<td><input type="password" name="password" id="pass" readonly required/></td>
				</tr>
				<tr></tr>
				<tr>
			</table>
			choose colour pattern used for Registration :
					<div>
					<a id ="green" class="button" onclick="onclickgreen();">Green</a>
					<a id ="blue" class="button button2" onclick="onclickblue();">Blue</a>
					<a id ="red" class="button button3" onclick="onclickred();">Red</a>
					</div>
			
			<input type="submit" value="Next" id="loginBtn"/>
		<input type="hidden" name="userName" value="${userName}">
        <input type="hidden" name="email" value="${email}">
         <input type="hidden" name="password" value="${password}">
			
			<!--   <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />-->
		</form>
</body>
</html>

</center>


</body>
</html>