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
<title>User Home Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>
	<c:url value="/searchRestaurant" var="userActionUrl" />
	<form action="${userActionUrl}" method="POST" class="form-horizontal">
		<div align="center">
			<div class="form-group">
				<label class="col-sm-2 control-label">Zip Code</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="zipCode"
						name="zipCode" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
			<div style="color: red">${error}</div>
		</div>
	</form>
	<c:url value="/trackOrder" var="trackOrder" />
		<div align="center">
			<div class="container">
  				<h2 color="red">My Orders</h2> 
  				<c:choose>
				<c:when test="${not empty myOrdersList}">           
  				<table class="table">
    				<thead>
      					<tr>
        					<th>Order Id</th>
      					</tr>
    				</thead>
    				<tbody>
    				<c:forEach var="o" items="${myOrdersList}">
      					<tr>
        					<td name="orderId" width="10%">${o}</td>
        					<td width="20%"><input type="button" class="btn btn-info" value="Track Order Details" name="Track" onclick="location.href='${trackOrder}/${o}/'"/></td>
      					</tr>
      					</c:forEach>
    				</tbody>
  				</table>
  				</c:when>
				<c:otherwise>There are no orders to be viewed</c:otherwise>
				</c:choose>
			</div>
		</div>	
		
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>