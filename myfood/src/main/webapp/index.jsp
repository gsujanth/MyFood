<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<a href="/views/login.jsp"></a>
<c:url value="/menuList" var="showMenuUrl" />
<button class="btn btn-info" onclick="location.href='${showMenuUrl}/1'">Show Menu</button>
</body>
</html>
