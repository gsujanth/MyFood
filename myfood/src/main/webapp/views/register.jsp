<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Customer Registration</title>
<jsp:include page="header2.jsp" />
<script type="text/javascript">
function validate(){
	var emailId = document.getElementById('email').value;
	var firstName = document.getElementById('firstName').value;
	var lastName = document.getElementById('lastName').value;
	var mobileNumber = document.getElementById('mobileNumber').value;
	var address = document.getElementById('address').value;
	var zipcode = document.getElementById('zipCode').value;
	var password = document.getElementById('password').value;
	
	var errorMessage = '';
	var validate = true;
	
	if(emailId == null || emailId == ''){
		errorMessage = errorMessage + 'Email Id missing.';
		validate = false;
	}
	var atposition=emailId.indexOf('@');  
	var dotposition=emailId.lastIndexOf('.');  
	if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length){  
	  errorMessage = errorMessage + 'Please enter a valid e-mail address';
	  validate = false;
	}
	
	if(firstName == null || firstName == ''){
		errorMessage = errorMessage + 'First Name missing.';
		validate = false;
	}
	if(lastName == null || lastName == ''){
		errorMessage = errorMessage + 'Last Name missing.';
		validate = false;
	}
	if(mobileNumber == 0 || isNan(mobileNumber)){
		errorMessage = errorMessage + 'Mobile Number invalid.';
		validate = false;
	}
	if(address == null || address == ''){
		errorMessage = errorMessage + 'address missing.';
		validate = false;
	}
	if(zipcode == 0 || isNaN(zipcode)){
		errorMessage = errorMessage + 'Zipcode Invalid.';
		validate = false;
	}
	if(password == null || password == ''){
		errorMessage = errorMessage + 'password missing.';
		validate = false;
	}
	
	alert('validate:'+validate);
	document.getElementById('errorMsg').value = errorMessage;
	
	return false;
}
</script>
</head>
<body>

<h2>My Food Profile Page</h2>
<p class="text-success">${SuccessMsg}</p>
<h3 class="text-danger" id="errorMessage">${errorMsg}</h3>

<c:url value="/register" var="userActionUrl" />
<form:form action="${userActionUrl}" method="POST" modelAttribute="customer" class="form-horizontal" onsubmit="return validate();">
  <div class="form-group">
    <label class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <form:input path="email" type="text" class="form-control" id="email"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">First Name</label>
    <div class="col-sm-10">
      <form:input path="firstName" type="text" class="form-control" id="firstName"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Last Name</label>
    <div class="col-sm-10">
      <form:input path="lastName" type="text" class="form-control" id="lastName"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Mobile Number</label>
    <div class="col-sm-10">
      <form:input path="mobileNumber" type="tel" class="form-control" id="mobileNumber"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Address</label>
    <div class="col-sm-10">
      <form:input path="address" type="text" class="form-control" id="address"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Zip Code</label>
    <div class="col-sm-10">
      <form:input path="zipCode" type="number" class="form-control" id="zipCode"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <form:input path="password" type="password" class="form-control" id="password"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Save</button>
    </div>
  </div>
</form:form>

</body>
</html>