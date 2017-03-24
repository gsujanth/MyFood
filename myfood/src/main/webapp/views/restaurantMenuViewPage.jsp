<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Restaurant Menu View Page</title>
<jsp:include page="header.jsp" />
</head>
<body>
<h1>Menu</h1>

<c:forEach items="${menuMap}" var="mapElement" varStatus="theCount">
	<p><button class="btn btn-success" type="button" data-toggle="collapse" data-target="#divID${theCount.index}" aria-expanded="false" aria-controls="divID${theCount.index}">
          ${mapElement.key}
    </button></p>
	<div class="collapse" id="divID${theCount.index}">
	 <table class="table table-hover">
       <c:forEach items="${mapElement.value}" var="menuItem" >
         <tr>
           <td></td>
           <td width="30%">${menuItem.itemName}</td>
           <td>
           <fmt:formatNumber type="currency" currencySymbol="$" value="${menuItem.cost}" />
           </td>
           <td>${menuItem.calories} cals</td>
           <td><button class="btn btn-info">Add to Cart</button></td>
         </tr>
        </c:forEach>
     </table>
    </div>
   </c:forEach>
</body>
</html>