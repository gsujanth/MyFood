<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Payment Confirmation Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body style="height: 100%; width: 100%; min-height: 0px;">
	<div id="container" style="width: 100%; height: 100%; float: left;">
		<div id="container-top"
			style="width: 100%; height: 100%; float: left;">
			<div id="container-top-left"
				style="width: 50%; height: 100%; float: left;">
				<h4 style="padding-left: 10%;">Order Details</h4>
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
								<tr>
									<td colspan="3"><h2>No Items in Order.</h2></td>
								</tr>
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
					<h3 style="float: right; margin-right: 10%;">
						Total Cost:
						<fmt:formatNumber type="currency" currencySymbol="$"
							value=" ${totalItemsCost}" />
					</h3>
				</div>

			</div>
			<div id="container-top-right"
				style="width: 50%; height: 100%; float: left;">
				
				<h3 style="float: left; margin-right: 10%;">Order Confirmation
					Id: ${OrderConfirmationId}</h3>
				<h3 style="float: left; margin-right: 10%;">Payment
					Confirmation Id: ${PaymentConfirmationId}</h3>
			</div>
		</div>
	</div>
</body>