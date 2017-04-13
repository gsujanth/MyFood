<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function () {

	
	$("#cardNumber").keypress(function (key) { 
		//	alert(key.which);
		var keycode = (key.which) ? key.which : key.keyCode;		
		if(key.which == 32 || (key.which != 8 && isNaN(String.fromCharCode(key.which))))
		{
       		key.preventDefault(); //stop character from entering input
    	}
	});
	$("#cVV").keypress(function (key) { 
		//	alert(key.which);
		var keycode = (key.which) ? key.which : key.keyCode;		
		if(key.which == 32 || (key.which != 8 && isNaN(String.fromCharCode(key.which))))
		{
       		key.preventDefault(); //stop character from entering input
    	}
	});
	$("#mobileNumber").keypress(function (key) { 
		//	alert(key.which);
		var keycode = (key.which) ? key.which : key.keyCode;		
		if(key.which == 32 || (key.which != 8 && isNaN(String.fromCharCode(key.which))))
		{
       		key.preventDefault(); //stop character from entering input
    	}
	});
	
	


});
function validate(){
	
	var valid = true;	
	var numberRegExp = new RegExp('^[0-9]+$');
	var fullDate = new Date();
	var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
	
	
	$("#cardNoErr").hide();
	$("#cvvErr").hide();
	$("#expiresOnErr").hide();
	$("#nameOnCardErr").hide();
	$("#customerNameErr").hide();
	$("#mobileNumberErr").hide();
	$("#addressErr").hide();
	
	
	//document.getElementById("cardNoErr").style.display="none";
	
	
	var cardNumber=$.trim($("#cardNumber").val());
	var cvv=$.trim($("#cVV").val());
	var expiryMonth=$.trim($("#expiryMonth").val());
	var expiryYear=$.trim($("#expiryYear").val());
	var cardName=$.trim($("#cardName").val());
	var customerName=$.trim($("#customerName").val());
	var mobileNumber=$.trim($("#mobileNumber").val());
	var address=$.trim($("#address").val());
	
	
	if(cardNumber == '' || cardNumber.length != 16 || !cardNumber.match(numberRegExp)){
		$("#cardNoErr").show();
		valid = false;
	}
	if(cvv == '' || cvv.length != 3 || !cvv.match(numberRegExp)){
		$("#cvvErr").show();
		valid = false;
	}
	if (expiryMonth == "00" || expiryYear == "0000"|| (expiryMonth < twoDigitMonth  &&  expiryYear <= fullDate.getFullYear()) ) {
		$("#expiresOnErr").show();
        valid = false;
    }
	if(cardName == ''){
		$("#nameOnCardErr").show();
		valid = false;
	}
	if(customerName == ''){
		$("#customerNameErr").show();
		valid = false;
	}
	if(mobileNumber == '' || mobileNumber.length != 10 || !mobileNumber.match(numberRegExp)){
		$("#mobileNumberErr").show();
		valid = false;
	}
	if(address == ''){
		$("#addressErr").show();
		valid = false;
	}
	
	
	//alert('test');
	if(true == valid){
		localStorage.setItem("itemCount", 0);
		document.getElementById("myForm").submit();	
	}
	//document.getElementById("myForm").submit();
	
}
</script>
	
<title>Payment Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body style="height:100%; width:100%; min-height: 0px;">
	<h1 style="text-align: center;">Payment Page</h1>
	<c:url value="/paymentConfirmation" var="paymentConfirmation" />
	<form:form id="myForm" action="${paymentConfirmation}" method="POST"
		class="form-horizontal">
		<div id="container" style="width: 100%; height: 100%;float:left;">
			<div id="container-top" style="width: 100%; height: 100%;float:left;">
				<div id="container-top-left"
					style="width: 50%; height: 100%; float: left;">
					<h4 style="padding-left: 10%;">Cart Details</h4>
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
						<h3 style="float: right; margin-right: 10%;">
							Total Cost:
							<fmt:formatNumber type="currency" currencySymbol="$"
								value=" ${totalItemsCost}" />
						</h3>
					</div>

				</div>
				<div id="container-top-right"
					style="width: 50%; height: 100%; float: left;">
					<h4 style="padding-left: 10%;">Enter Payment Card Details</h4>

					<a id="cardNoErr" class="error"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 200px; margin-left: 125px;">Please
							enter a valid Card number.</a>
					<p style="margin: 1 0 1em; text-align: left;">
					
						 <strong
							style="font-size: 1em; font-weight: normal; padding-top: 20px; padding-left: 10px; width: 120px; display: inline-block;">Card
							No.*:</strong>
							<input type="text" autocomplete="off" id="cardNumber"
							name="cardNumber" maxlength="16" size="55"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
					</p>


					<a id="cvvErr" class="error"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 200px; margin-left: 125px;">Please
							enter a valid CVV number.</a>
					<a id="expiresOnErr"
						style="display: none; background-color: #dd4d3b; color: #fff; width: 220px; margin-left: 10px;">Please
						select your Card expiry date.</a>
						
					<p style="margin: 1 0 1em; text-align: left;">
						<strong
							style="font-size: 1em; font-weight: normal; width: 120px; padding-left: 10px; display: inline-block;">CVV
							No.*:</strong>
							<input type="password" autocomplete="off" id="cVV"
							name="cVV" maxlength="3" class="cvv" size="5"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">

						<strong
							style="text-align: right; font-size: 1em; font-weight: normal; width: 120px; display: inline-block;">Expires
							on*: &nbsp;</strong> <select id="expiryMonth" name="expiryMonth"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
							<option value="00">Month</option>
							<option value="01">01 (Jan)</option>
							<option value="02">02 (Feb)</option>
							<option value="03">03 (Mar)</option>
							<option value="04">04 (Apr)</option>
							<option value="05">05 (May)</option>
							<option value="06">06 (Jun)</option>
							<option value="07">07 (Jul)</option>
							<option value="08">08 (Aug)</option>
							<option value="09">09 (Sep)</option>
							<option value="10">10 (Oct)</option>
							<option value="11">11 (Nov)</option>
							<option value="12">12 (Dec)</option>
						</select> <select id="expiryYear" name="expiryYear"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
							<option value="00">Year</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
							<option value="2026">2026</option>
							<option value="2027">2027</option>
							<option value="2028">2028</option>
							<option value="2029">2029</option>
							<option value="2030">2030</option>
							<option value="2031">2031</option>
							<option value="2032">2032</option>
							<option value="2033">2033</option>
							<option value="2034">2034</option>
							<option value="2035">2035</option>
							<option value="2036">2036</option>
							<option value="2037">2037</option>
						</select>

					</p>


					<p id="nameOnCardErr"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 240px; margin-left: 125px;">Please
							enter your Name as on Card.</p>
					<div>
						<strong
							style="font-size: 1em; font-weight: normal; padding-left: 15px; width: 120px; display: inline-block;">Name
							on card*:</strong> <input type="text" maxlength="100" autocomplete="off"
							id="cardName" name="cardName" size="55"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
					</div>
					
					<h4 style="padding-left: 10%; padding-top: 2%;">Enter Delivery Details</h4>
					
					<a id="customerNameErr" class="error"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 200px; margin-left: 125px;">Please
							enter a valid Name.</a> 
					<p style="margin: 1 0 1em; text-align: left;">
						<strong
							style="font-size: 1em; font-weight: normal; padding-top: 20px; padding-left: 10px; width: 120px; display: inline-block;">Name*:
							</strong> <input type="text" id="customerName"
							name="customerName" size="55" value="${customerDetails.firstName} ${customerDetails.lastName}"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
					</p>
					
					<a id="mobileNumberErr" class="error"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 200px; margin-left: 125px;">Please
							enter a valid Mobile Number.</a> 
					<p style="margin: 1 0 1em; text-align: left;">
						<strong
							style="font-size: 1em; font-weight: normal; padding-top: 20px; padding-left: 10px; width: 120px; display: inline-block;">Mobile Number*:
							</strong> <input type="number" id="mobileNumber"
							name="mobileNumber" size="55" value="${customerDetails.mobileNumber}"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
					</p>
					
					<a id="addressErr" class="error"
							style="display: none; background-color: #dd4d3b; color: #fff; width: 200px; margin-left: 125px;">Please
							enter a valid Address.</a> 
					<p style="margin: 1 0 1em; text-align: left;">
						<strong
							style="font-size: 1em; font-weight: normal; padding-top: 20px; padding-left: 10px; width: 120px; display: inline-block;">Address*:
							</strong> <input type="text" id="address"
							name="address" size="55" value="${customerDetails.address}"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;">
					</p>
					
					<p style="margin: 1 0 1em; text-align: left;">
						<strong
							style="font-size: 1em; font-weight: normal; padding-top: 20px; padding-left: 10px; width: 120px; display: inline-block;">Zipcode:
							</strong> <input type="number" id="zipCode" 
							name="zipCode" size="55" value="${customerDetails.zipCode}"
							style="border: 1px solid #bababa; height: 30px; background-color: white; -webkit-border-radius: 3px 3px 3px 3px;" readOnly>
					</p>
					<input type="hidden" name="totalAmount" id="totalAmount" value="${totalItemsCost}">
					
					<c:choose>
    					<c:when test="${cartSize == 0}">
       						<button type="button" onclick="validate(); return false;" 
					class="btn-lg btn-primary disabled" disabled>Confirm Payment</button>
    					</c:when>
    					<c:otherwise>
        					<button type="button" onclick="validate(); return false;" 
					class="btn-lg btn-primary" >Confirm Payment</button>
    					</c:otherwise>
					</c:choose>
					
					
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>