<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<html>

<head>
<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}
.col-xs-4 {
   padding:4px;
}

body {
height : 80%;
background-repeat: no-repeat;
z-index: -1;
background-position: right;
background-color: #8bc34a;
background-size: 25%;
}

*[role="form"] {
	max-width: 70%;
	max-height:70%;
	padding: 15px;
	border-radius: 0.3em; 
    margin-right:20%;
    margin-top: auto;
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

#nextBtn {
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
#nextBtn:hover {
    -webkit-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -moz-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -webkit-transition: all 0.4s ease;
    -moz-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

</style>



<body onload="loadImages()">

	<center>

		<h3>Level2 Authentication</h3>

		<br />

		<form name="validateImage" action="<c:url value='/validateImage' />" method='POST' role="form" enctype="multipart/form-data">
			<div class="row">
				<div class="col-sm-4">
					<c:forEach items="${imageList}" var="image">
						<input type="checkbox" name="imageSource" value="${image}" style="margin-right: .5rem;">
						<img src="data:image/jpg;base64,${image}" style="width: 150px" />
				</c:forEach>
			</div>
			</div>
			<!-- <div>
				<span id="result" align="center"> </span>
			</div> -->

			<input type="submit" value="Next" id="nextBtn"/> 
			<input type="hidden" name="userName" value="${userName}"> 
			<input type="hidden" id="email" name="email" value="${email}">
			<input type="hidden" name="password" value="${password}">
			<input type="hidden" name="imageSource" value="data:image/jpg;base64,${image}">
		</form>
</body>
</html>

</center>

</body>

</html>