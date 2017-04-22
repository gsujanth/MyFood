<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>View Orders</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body>
	<p class="text-success">${SuccessMsg}</p>
	<h3 class="text-danger" id="errorMessage">${errorMsg}</h3>

	<c:url value="/orderConfirmation" var="userActionUrl" />

	<h5>Following orders are placed for restaurant:</h5>
	<c:choose>
		<c:when test="${not empty ordersList}">
    <table id="myTable" border="2">
			   
				<tr>
					<th>Order Id</th>
					<th>Customer Id</th>
					<th>Restaurant Id</th> 
				<c:forEach var="o" items="${ordersList}">
				<tr>
                <td width="10%">${o.orderId}</td>
				<td width="20%">${o.customerId}</td>
				<td width="20%">${o.restaurantId}</td>
				<td width="20%"><input type="button"
						value="Confirm/Cancel Order" name="Confirm"
						onclick="location.href='${userActionUrl}/${o.orderId}'" /></td>
				</tr>
                </c:forEach> 
				</tr>
	</table>
		</c:when>
		<c:otherwise>There are no orders to be viewed</c:otherwise>
	</c:choose>
</body>
</html>
