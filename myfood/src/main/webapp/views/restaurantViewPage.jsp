<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Restaurant View Page</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<h1>Restaurant List</h1>
	<c:url value="/menuList" var="showMenuUrl" />
	<table class="table table-hover">
	<tr>
				<td width="30%">Restaurant Name</td>
				<td>Zipcode</td>
				<td>Cuisine</td>
				<td>Address</td>
				<td></td>
	</tr>
		<c:forEach items="${restaurantList}" var="restaurant">
			<tr>
				<td width="30%">${restaurant.restaurantName}</td>
				<td>${restaurant.pincode}</td>
				<td>${restaurant.cuisine}</td>
				<td>${restaurant.address}</td>
				<td><button class="btn btn-info" onclick="location.href='${showMenuUrl}/${restaurant.restaurantId}'">Show Menu</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>