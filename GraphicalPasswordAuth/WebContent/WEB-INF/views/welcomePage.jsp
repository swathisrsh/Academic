<!DOCTYPE html>
<html>
<head>
<style>
body {
height : 70%;
background-image: url('/SpringMVCLogin/urlToReach/image/home.JPG'); 
background-size:  cover;
background-repeat: no-repeat;
z-index: -1;
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
	display: inline-block;
}

.button {
	font: bold 14px Arial;
	
	text-decoration: none;
	background-color: #EEEEEE;
	color: #333333;
	padding: 2px 6px 2px 6px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}

#status {
   position: relative;
   font: bold 24px;
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

#contBtn {
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
#contBtn:hover {
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
	<form role="form"  >
	<h1 id="status"><b>GRAPHICAL PASSWORD AUTHENTICATION</b></h1>
	            <tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
		<table align="center">
			<tr>
				<td><a type="submit" id="contBtn"
					href="${pageContext.request.contextPath}/register" value="Register"><b>Sign Up!</b></a>
				</td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
				<td><a type="submit" id="contBtn"
					href="${pageContext.request.contextPath}/loginPage" value="Login"><b>Login</b></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>