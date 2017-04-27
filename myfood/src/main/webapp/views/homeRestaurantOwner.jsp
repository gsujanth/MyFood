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
	<c:url value="/viewOrders" var="viewOrders" />
	<c:url value="/addMenuItem" var="addMenuItem" />
	<c:url value="/viewMenuItems" var="viewMenuItems" />
	<c:url value="/viewConfirmedOrders" var="viewConfirmedOrders" />
	
		<div align="center">
			<div class="form-group">
				<input type="button" class="btn btn-primary btn-lg" value="View Orders Placed" name="View Orders" onclick="location.href='${viewOrders}'" />
				<input type="button" class="btn btn-primary btn-lg" value="Add Menu Items" name="Add Menu Items" onclick="location.href='${addMenuItem}'" /><br><br>
				<input type="button" class="btn btn-primary btn-lg" value="View Confirmed Orders" onclick="location.href='${viewConfirmedOrders}'" />				
				<input type="button" class="btn btn-primary btn-lg" value="View Menu Items" name="View Menu Items" onclick="location.href='${viewMenuItems}'"/>
			</div>
			<div style="color: red">${error}</div>
		</div>
	
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>