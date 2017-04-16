<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null
	&& session.getAttribute("userRole").equals("admin")){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>
<jsp:include page="headerAdmin.jsp" />
</head>
<body>
	<c:url value="/AddRestaurant" var="userActionUrl" />
	<c:url value="/AddRestaurant" var="addRest" />
	<c:url value="/ViewRestaurants" var="viewRest" />
	<form action="${userActionUrl}" method="POST" class="form-horizontal">
		<div align="center">
			<div class="form-group">
				<div class="col-sm-10">
				<input type="button" value="Add Restaurant" name="AddRestaurant" onclick="location.href='${addRest}'" />
				<input type="button" value="View & Delete Restaurants" name="ViewRestaurants" onclick="location.href='${viewRest}'" />
				</div>
			</div>
			<div style="color: red">${error}</div>
		</div>
	</form>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>