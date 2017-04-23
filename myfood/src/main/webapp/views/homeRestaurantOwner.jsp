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
<script type="text/javascript">
function generatenew(){
    var x=document.myform.itemtoremove;
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
    
    var y=document.myform.Remove;
    if (y.style.display === 'none') {
        y.style.display = 'block';
    } else {
        y.style.display = 'none';
    }
}

</script>
</head>
<body>
	<c:url value="/viewOrders" var="viewOrders" />
	<c:url value="/addMenuItem" var="addMenuItem" />
	<c:url value='/removeMenuItem("${itemId}")' var="removeMenuItem" />
	<c:url value="/viewConfirmedOrders" var="viewConfirmedOrders" />
	
		<div align="center">
			<div class="form-group">
				<input type="button" class="btn btn-primary btn-lg" value="View Orders Placed" name="View Orders" onclick="location.href='${viewOrders}'" />
				<input type="button" class="btn btn-primary btn-lg" value="Add Menu Items" name="Add Menu Items" onclick="location.href='${addMenuItem}'" /><br><br>
				<input type="button" class="btn btn-primary btn-lg" value="View Confirmed Orders" onclick="location.href='${viewConfirmedOrders}'" />				
				<input type="button" class="btn btn-primary btn-lg" value="Remove Menu Item" name="Remove Menu Items" onclick="generatenew();">
				<input type="button" style="display:none" value="Remove" name="Remove" onclick="location.href='${removeMenuItem}'" />
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