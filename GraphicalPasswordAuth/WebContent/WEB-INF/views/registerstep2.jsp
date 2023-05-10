<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>File uploader</title>
<style type="text/css">
body {
	background-size: cover;
	background-color: #8bc34a;
}

*[role="form"] {
   max-width: 50%;
	padding: 15px;
	margin-left:20%; 
    margin-right:15%;
    margin-top: 15%;
    margin-bottom: 15%;
	border-radius: 0.3em;
	text-align: center;
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

#uploadBtn {
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
#uploadBtn:hover {
    -webkit-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -moz-box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    box-shadow: 0px 15px 30px rgba(60, 60, 60, 0.4);
    -webkit-transition: all 0.4s ease;
    -moz-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}
</style>
</head>
<body>
	<form action="<c:url value='/uploadFile'/>" method='POST'
		enctype="multipart/form-data" role="form">
		<h3>Upload File</h3>
		<table style="width: 50%">
			<tr>
				<td>
					<div class="form-group">
						<label for="formFile" class="form-label">Please Upload image file for second level check</label>
						<input name="thisfile" class="form-control" type="file"
							id="formFile">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<button id =" uploadBtn">Upload</button>
				</td>
			</tr>
			<input type="hidden" name="userName" value="${userName}">
			<input type="hidden" name="email" value="${email}">
			<input type="hidden" name="password" value="${password}">
		</table>
	</form>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>