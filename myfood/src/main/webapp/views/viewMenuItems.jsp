<%--sujanth--%>
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
<title>View Menu Items</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body>
<div align="center">
<c:url value="/removeMenuItem" var="removeMenuItem" />
<h3>Menu Items</h3>
<p class="text-success">${SuccessMsg}</p>
<c:choose>
				<c:when test="${not empty resMenuItems}">           
  				<table class="table table-hover">
    				<thead>
      					<tr class="info">
        					<th>Item Id</th>
        					<th>Item Name</th>
        					<th>Category</th>
        					<th>Calories</th>
        					<th>Cost</th>
      					</tr>
    				</thead>
    				<tbody>
    				<c:forEach var="o" items="${resMenuItems}">
      					<tr class=warning>
        					<td name="orderId" width="10%">${o.itemId}</td>
        					<td name="itemName" width="10%">${o.itemName}</td>
        					<td name="category" width="10%">${o.category}</td>
        					<td name="calories" width="10%">${o.calories}</td>
        					<td name="cost" width="10%">${o.cost}</td>
        					<td width="10%"><input type="button" class="btn btn-info" value="Remove Item" name="Remove Item" onclick="location.href='${removeMenuItem}/${o.itemId}/'"/></td>
      					</tr>
      					</c:forEach>
    				</tbody>
  				</table>
  				</c:when>
				<c:otherwise><h2>No menu items added</h2></c:otherwise>
				</c:choose>
</div>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>