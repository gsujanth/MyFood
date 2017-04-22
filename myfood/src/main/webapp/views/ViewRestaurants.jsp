<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null && session.getAttribute("userRole") != null
			&& session.getAttribute("userRole").equals("admin")) {
%>
<html>
<head>
<meta charset="utf-8">
<title>View & Delete Restaurant</title>
<jsp:include page="headerAdmin.jsp" />
</head>
<body>
	<p class="text-success">${SuccessMsg}</p>
	<h3 class="text-danger" id="errorMessage">${errorMsg}</h3>

	<c:url value="/ViewRestaurants" var="userActionUrl" />

	<h5>Following restaurants are listed in the application:</h5>
	<c:choose>
		<c:when test="${not empty restaurantList}">
    <table id="myTable" border="2">
				   
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Address</th>
					<th>Zip</th>
					<th>Cuisine</th>
				</tr>
				<c:forEach var="o" items="${restaurantList}">
                <td width="5%">${o.restaurantId}</td>
                <td width="20%">${o.restaurantName}</td>
                <td width="20%">${o.address}</td>
                <td width="20%">${o.pincode}</td>   
                <td width="20%">${o.cuisine}</td>
                <td width="20%"><input type="button"
						value="Delete From List" name="DeleteFromList"
						onclick="location.href='${userActionUrl}/${o.restaurantId}'" /></td> 
            </tr>
        </c:forEach>
				   
			</table>
		</c:when>
		<c:otherwise>There are no restaurants to be deleted from list</c:otherwise>
	</c:choose>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>