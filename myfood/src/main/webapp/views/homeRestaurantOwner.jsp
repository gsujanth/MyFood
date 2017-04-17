<!-- sujanth -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null
	&& session.getAttribute("userRole").equals("restaurantowner")){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Owner Home Page</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body>
	<c:url value="/ViewOrders" var="userActionUrl" />
	<c:url value="/ViewOrders" var="viewOrders" />
	<c:url value="/AddMenuItem" var="addMenuItem" />
	<c:url value="/RemoveMenuItem" var="removeMenuItem" />
	<form action="${userActionUrl}" method="POST" class="form-horizontal">
		<div align="center">
			<div class="form-group">
				<div class="col-sm-10">
				<input type="button" value="View Orders" name="View Orders" onclick="location.href='${viewOrders}'" />
				<input type="button" value="Add Menu Items" name="Add Menu Items" onclick="location.href='${addMenuItem}'" />
				<input type="button" value="Remove Menu Item" name="Remove Menu Items" onclick="location.href='${removeMenuItem}'" />
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