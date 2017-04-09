<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Payment Confirmation Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>
Total Item Cost: ${totalItemsCost}
Order Confirmation Id: ${OrderConfirmationId}
Payment Confirmation Id: ${PaymentConfirmationId}
</body>	
