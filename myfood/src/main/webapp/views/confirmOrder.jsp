<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>Order Confirmed</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body style="height: 100%; width: 100%; min-height: 0px;">
	<h1 style="text-align: center;"><font color="Green">Order Confirmed!!</font></h1>
	<c:url value="/viewOrders" var="userActionUrl" />
<form:form id="myForm" action="/views/viewOrders" method="POST"
		class="form-horizontal">
		<div id="container" style="width: 100%; height: 100%;float:left;">
			<div id="container-top" style="width: 100%; height: 100%;float:left;">
				<div id="container-top-left"
					style="width: 50%; height: 100%; float: left;">
					<h4 style="padding-left: 10%;"><font color="blue">Order Details</font></h4>
					
					<div id="container-top-left-top"
						style="width: 100%; height: 70%; overflow-y: scroll; float: left;">
						<table class="table table-hover">
							<tr>
								<td width="30%" style="text-align: left; padding-left: 10%;">Item
									Name</td>
								<td style="text-align: center;">Quantity</td>
								<td style="text-align: center;">Cost (in $)</td>
							</tr>
							<c:choose>
								<c:when test="${cartSize == 0}">
									<tr><td colspan="3"><h2>No Items in Cart.</h2></td></tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${cartItems}" var="cartItem">
										<tr>
											<td width="40%" style="text-align: left; padding-left: 10%;">${cartItem.itemName}</td>
											<td style="text-align: center;">${cartItem.itemQuantity}</td>
											<td style="text-align: center;">${cartItem.itemCost}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
					<div id="container-top-left-bottom"
						style="width: 100%; height: 10%; float: left;">
						<h4 style="float: right; margin-right: 10%;">
							Total Cost:
							<fmt:formatNumber type="currency" currencySymbol="$"
								value=" ${totalItemsCost}" />
						</h4>
					</div>

				</div>
					<div id="container-top-left-top"
						style="width: 50%; height: 100%; overflow-y: scroll; float: left;">
					<h4 style="padding-left: 10%;"><font color="blue">Customer Details</font></h4>		
					<table>
					<tr><td width="400" align="left" valign="top" style="padding-left: 60px; padding-right: 10px;font-family:Arial; font-size: 16px;"><p>${customerDetails.firstName} ${customerDetails.lastName}</p></td></tr>
					<tr><td width="389" align="left" valign="top" style="padding-left: 60px; padding-right: 10px;font-family:Arial; font-size: 16px;"><p>${customerDetails.mobileNumber}</p></td></tr>
					<tr><td width="389" align="left" valign="top" style="padding-left: 60px; padding-right: 10px;font-family:Arial; font-size: 16px;"><p>${customerDetails.address}</p></td></tr>
					<tr><td width="389" align="left" valign="top" style="padding-left: 60px; padding-right: 10px;font-family:Arial; font-size: 16px;"><p>${customerDetails.zipCode}</p></td></tr>
					<tr><td width="389" align="left" valign="top" style="padding-left: 60px; padding-right: 10px;font-family:Arial; font-size: 16px;"><p><font color="magenta"><b>Estimated Time: ${hh} hours : ${mm} minutes </b></font></p></td></tr>
					</table>
				
					<input type="hidden" name="totalAmount" id="totalAmount" value="${totalItemsCost}">
				</div>
				<div id="container-top-left-top"
						style="width: 50%; height: 100%; overflow-y: scroll; float: left;">
						<table>
						<tr><td style="padding-left: 60px; padding-right: 10px;"><input type="button" name="Return" class="btn-lg btn-primary" id="Return" value="Return To View Orders" onclick="location.href='${userActionUrl}'"></td>
						</tr>
						</table>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>