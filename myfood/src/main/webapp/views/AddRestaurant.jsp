<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Restaurant Registration</title>
<jsp:include page="headerAdmin.jsp" />
<script type="text/javascript">
function validate(){
	var restaurantName = document.myform.restaurantName.value;
	var address = document.myform.address.value;
	var zipcode = document.myform.pincode.value;
	var cuisine = document.myform.cuisine.value;
	
	var errorMessage = '';
	var validate = true;
	
	if(restaurantName == null || restaurantName == ''){
		errorMessage = errorMessage + 'Restaurant Name missing.';
		alert("Restaurant Name missing.");
		return false;
	}
	if(address == null || address == ''){
		errorMessage = errorMessage + 'Address missing.';
		alert("address missing.");
		return false;
	}
	if(zipcode == 0 || isNaN(zipcode)){
		errorMessage = errorMessage + 'Zip Code missing.';
		alert("Zipcode Invalid.");
		return false;
	}
	if(cuisine == null || cuisine == ''){
		errorMessage = errorMessage + 'Cuisine missing.';
		alert("cuisine missing.");
		return false;
	}
	document.getElementById('errorMsg').value = errorMessage;
}
</script>
</head>
<body>

<h4>Please enter the details of restaurant:</h4>
<p class="text-success">${SuccessMsg}</p>
<h3 class="text-danger" id="errorMessage">${errorMsg}</h3>

<c:url value="/AddRestaurant" var="userActionUrl" />
<form:form name="myform" action="${userActionUrl}" method="POST" modelAttribute="restaurant" class="form-horizontal" onsubmit="return validate();">
  <div class="form-group">
    <label class="col-sm-2 control-label">Restaurant Name</label>
    <div class="col-sm-10">
      <form:input path="restaurantName" type="text" class="form-control" name="restaurantName"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Address</label>
    <div class="col-sm-10">
      <form:input path="address" type="text" class="form-control" name="address"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Zip Code</label>
    <div class="col-sm-10">
      <form:input path="pincode" type="number" class="form-control" name="pincode"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Cuisine</label>
    <div class="col-sm-10">
      <form:input path="cuisine" type="text" class="form-control" name="cuisine"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Register</button>
    </div>
  </div>
</form:form>

</body>
</html>