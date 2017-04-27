<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null
	&& session.getAttribute("userRole").equals("user")){
%>
<html>
<head>
<title>Track Order Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>
<div align="center">
<h3>Order Details</h3>
<c:choose>
				<c:when test="${not empty myOrderDetails}">           
  				<table class="table table-hover">
    				<thead>
      					<tr class="info">
        					<th>Order Id</th>
        					<th>Restaurant Id</th>
        					<th>Order Status</th>
        					<th>Comments</th>
        					<th>Ordered Date</th>
        					<th>Estimated Delivery Time</th>
      					</tr>
    				</thead>
    				<tbody>
    				<c:forEach var="o" items="${myOrderDetails}">
      					<tr class=warning>
        					<td name="orderId" width="10%">${o.orderId}</td>
        					<td name="orderId" width="10%">${o.restaurantId}</td>
        					<td name="orderId" width="10%">${o.status}</td>
        					<td name="orderId" width="10%">${o.comments}</td>
        					<td name="orderId" width="10%">${o.createdOn}</td>
        					<td name="orderId" width="10%">${o.estimatedTime}</td>
      					</tr>
      					</c:forEach>
    				</tbody>
  				</table>
  				</c:when>
				<c:otherwise>The order is not approved</c:otherwise>
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