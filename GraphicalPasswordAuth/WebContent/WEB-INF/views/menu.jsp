<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "user" value = "${user}"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
   
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/" />">Home</a></li>
            <c:if test="${not empty user}">
                <li><a id="profile-button" href="<c:url value="/profile" />">My Profile</a></li>
            </c:if>
        </ul>
           <ul class="nav navbar-right">
           <c:if test="${not empty user}">
             <li>${user}</li>
              <li>${loggedIn}</li>
             </c:if>
           </ul>  
        <c:if test="${not empty user}">
            <form method="post" action="<c:url value="/logout"/>" class="navbar-form navbar-right">
                <button id="logout-button" type="submit" class="btn btn-danger">Logout</button>
            </form>
        </c:if>
    </div>
</nav>