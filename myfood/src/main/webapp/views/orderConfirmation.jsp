<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null && session.getAttribute("userRole") != null
			&& session.getAttribute("userRole").equals("restaurantowner")) {
%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
	
</script>
<script type="text/javascript">
$(document).ready(function () {

	
	$("#hh").keypress(function (key) { 
		//	alert(key.which);
		var keycode = (key.which) ? key.which : key.keyCode;		
		if(key.which == 32 || (key.which != 8 && isNaN(String.fromCharCode(key.which))))
		{
       		key.preventDefault(); //stop character from entering input
    	}
	});
	$("#mm").keypress(function (key) { 
		//	alert(key.which);
		var keycode = (key.which) ? key.which : key.keyCode;		
		if(key.which == 32 || (key.which != 8 && isNaN(String.fromCharCode(key.which))))
		{
       		key.preventDefault(); //stop character from entering input
    	}
	});
});

	function validateDeliveryTime() {
		var orderId = document.getElementById("orderId").value;
		var hh = document.getElementById("hh").value;
		var mm = document.getElementById("mm").value;
		typeof hh;
		typeof mm;
		if (hh == null || hh == '') {
			alert("Please enter estimated hours of delivery in hh format.");
			return false;
		}
		else if (hh < 0 || hh >= 24) {
			alert("estimated hours should be between 0 and 23.");
			return false;
		}
		else if (mm == null || mm == '') {
			alert("Please enter estimated minutes of delivery in mm format.");
			return false;
		}
		else if (mm < 0 || mm >= 60) {
			alert("estimated minutes should be between 0 and 59.");
			return false;
		}
		else if (hh == 0 && mm == 0) {
			alert("both estimated hours and estimated minutes cannnot be 0.");
			return false;
		}
		else{
			$(location).attr('href', '/myfood/confirmOrder/'+orderId+"/"+hh+"/"+mm);
		}
		return true;
	}
</script>
<script type="text/javascript">
	function validateComments() {
		var orderId = document.getElementById("orderId").value;
		var comments = document.getElementById("comments").value;
		typeof comments;
		if (comments == null || comments == '') {
			alert("Please enter valid comment for canceling the order");
			document.getElementById("comments").focus();
			return false;
		}
		else if(comments.length <= 20){
			alert("Comments should have a length of at least 20 characters.");
			document.getElementById("comments").focus();
			return false;
		}
		else{
			$(location).attr('href', '/myfood/cancelOrder/'+orderId+"/"+comments);
		}
		//return true;
	}
</script>
<title>Confirm Or Cancel Order</title>
<jsp:include page="headerRestaurantOwner.jsp" />
</head>
<body style="height: 100%; width: 100%; min-height: 0px;">
	<p class="text-success">${SuccessMsg}</p>
	<h1 style="text-align: center;">Order Summary</h1>
	<c:url value="/cancelOrder" var="userActionUrl" />
	<c:url value="/confirmOrder" var="userActionUrlConfirm" />

	<div id="container" style="width: 100%; height: 100%; float: left;">
		<div id="container-top"
			style="width: 100%; height: 100%; float: left;">
			<div id="container-top-left"
				style="width: 50%; height: 100%; float: left;">
				<h4 style="padding-left: 10%;">
					<font color="blue">Order Details</font>
				</h4>

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
									<td colspan="3"><h2>No Items in Cart.</h2></td>
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
					<h4 style="float: right; margin-right: 10%;">
						Total Cost:
						<fmt:formatNumber type="currency" currencySymbol="$"
							value=" ${totalItemsCost}" />
					</h4>
				</div>

			</div>
			<div id="container-top-left-top"
				style="width: 50%; height: 100%; overflow-y: scroll; float: left;">
				<h4 style="padding-left: 10%;">
					<font color="blue">Customer Details</font>
				</h4>
				<table>
					<tr>
						<td width="400" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial; font-size: 16px;"><p>${customerDetails.firstName}
								${customerDetails.lastName}</p></td>
					</tr>
					<tr>
						<td width="389" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial; font-size: 16px;"><p>${customerDetails.mobileNumber}</p></td>
					</tr>
					<tr>
						<td width="389" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial; font-size: 16px;"><p>${customerDetails.address}</p></td>
					</tr>
					<tr>
						<td width="389" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial; font-size: 16px;"><p>${customerDetails.zipCode}</p></td>
					</tr>
				</table>

				<input type="hidden" name="totalAmount" id="totalAmount"
					value="${totalItemsCost}">
			</div>
			<div id="container-top-left-top"
				style="width: 50%; height: 100%; overflow-y: scroll; float: left;">
				<table>
					<tr>
						<td width="400" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial;"><label>Estimated
								Delivery Time </label></td>
						<td align="center"><input type="text" id="hh"><label>(hh)</label></td>
						<td width="20" align="left" valign="top"
							style="padding-left: 5px; padding-right: 5px; font-family: Arial;"><label>:</label></td>
						<td align="center"><input type="text" id="mm"><label>(mm)</label></td>
					</tr>
					<tr>
						<td width="400" align="left" valign="top"
							style="padding-left: 60px; padding-right: 10px; font-family: Arial;"><label>Reason
								for cancellation</label></td>
						<td>
							<input type="text" id="comments" name="comments">
							<input type="hidden" id="orderId" name="orderId" value="${orderId}">
						</td>
					</tr>
				</table>
				<table>
				
					<tr>
						<%-- <td style="padding-left: 60px; padding-right: 10px;"><input
							type="submit" name="Confirm" class="btn-lg btn-primary"
							id="Confirm" value="Confirm"
							onclick="location.href='${userActionUrlConfirm}/${orderId}/'+document.getElementById('hh').value+'/'+document.getElementById('mm').value+validateDeliveryTime();"></td>
						<td><input type="submit" name="Cancel"
							class="btn-lg btn-primary" id="Cancel" value="Cancel"
							onclick="location.href='${userActionUrl}/${orderId}/'+document.getElementById('comments').value+validateComments();">
						</td> --%>
					</tr> 
					<tr>
						<td style="padding-left: 60px; padding-right: 10px;"><input
							type="button" name="Confirm" class="btn-lg btn-primary"
							id="Confirm" value="Confirm"
							onclick="validateDeliveryTime();"></td>
						<td><input type="button" name="Cancel"
							class="btn-lg btn-primary" id="Cancel" value="Cancel"
							onclick="validateComments();">
						</td>
					</tr>
				</table>
			</div>
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