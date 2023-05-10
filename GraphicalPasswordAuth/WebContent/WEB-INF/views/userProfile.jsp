
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/header.jsp" />
<body id="samples">
	<jsp:include page="/WEB-INF/views/menu.jsp" />

	<div id="content" class="container">

		<div>
			<h2>My Profile</h2>
		</div>
		<table>
			<tr>
				<td><label class="bold">UserName: </label></td>
				<td></td>
				<td>${user}</td>
			</tr>
			<tr>
				<td><label class="bold">Email:</label></td>
				<td></td>
				<td>${email}</td>
			</tr>
		</table>
	</div>
</body>