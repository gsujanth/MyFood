<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null
	&& session.getAttribute("userRole").equals("restaurantowner")){
%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<title>Confirmed Orders</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body>
<h2>Confirmed Orders</h2>
<c:url value="/updateStatus" var="updateStatusUrl" />
	<table class="table noborder table-hover">
	   <tr>
	        <td></td>
			<td>Order</td>
			<td>Status</td>
			<td></td>
	   </tr>
	   <c:forEach items="${confirmedOrders}" var="order">
	   <tr>
	   	  <td width="30%">
			  <input type="text" name="orderId" class="form-control" value="ORDER${order.orderId}" readOnly/>
		  </td>
		  <td width="30%">
			  <select name="status">
				    <c:forEach var="orderStatus" items="${orderStatusList}">
				        <option value="${orderStatus}" ${orderStatus == order.status ? 'selected="selected"' : ''}>${orderStatus}</option>
				    </c:forEach>
				</select>
		  </td>
		  <td>
		  	<button class="btn btn-default" onclick="location.href='${updateStatusUrl}/${order.orderId}/'+$(this).parents('tr').find('select').eq(0).val();">UPDATE</button>
		  </td>
	   </tr>
	   </c:forEach>
	</table>

</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>