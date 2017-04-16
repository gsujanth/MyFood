<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null){
%>
<html>
<head>
<meta charset="utf-8">
<title>Edit Profile</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>

<h2>My Food Profile Page</h2>
<p class="text-success">${SuccessMsg}</p>

<c:url value="/editUserProfile" var="userActionUrl" />
<form:form action="${userActionUrl}" method="POST" modelAttribute="customer" class="form-horizontal">
  <form:hidden path="customerId" />
  <form:hidden path="email" />
  <div class="form-group">
    <label class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <p class="form-control-static">${customer.email}</p>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">First Name</label>
    <div class="col-sm-10">
      <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="${customer.firstName}" />
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Last Name</label>
    <div class="col-sm-10">
      <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="${customer.lastName}" />
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Mobile Number</label>
    <div class="col-sm-10">
      <form:input path="mobileNumber" type="tel" class="form-control" id="mobileNumber" placeholder="${customer.mobileNumber}" />
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Address</label>
    <div class="col-sm-10">
      <form:input path="address" type="text" class="form-control" id="address" placeholder="${customer.address}" />
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Zip Code</label>
    <div class="col-sm-10">
      <form:input path="zipCode" type="number" class="form-control" id="zipCode" placeholder="${customer.zipCode}" />
    </div>
  </div>
  <form:hidden path="password" />
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Save</button>
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