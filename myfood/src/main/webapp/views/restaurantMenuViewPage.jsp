<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Restaurant Menu View Page</title>
<jsp:include page="header.jsp" />
</head>
<body>
<h1>Menu Profile Page</h1>
<table class="table table-hover">
		<thead>
			<tr>
				<th>Category</th>
				<th>ItemName</th>
				<th>Cost</th>
				<th>Calories</th>
				<th></th>
			</tr>
		</thead>
   		<c:forEach items="${menuMap}" var="mapElement">
          <c:forEach items="${mapElement.value}" var="menuItem" >
            <tr>
              <td>${mapElement.key}</td>
              <td>${menuItem.itemName}</td>
              <td>
              <fmt:formatNumber type="currency" currencySymbol="$" value="${menuItem.cost}" />
              </td>
              <td>${menuItem.calories}</td>
              <td><button class="btn btn-info">Add to Cart</button></td>
            </tr>
           </c:forEach>
   </c:forEach>
</table>
</body>
</html>