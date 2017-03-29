<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="menuList/1">ShowMenu</a>
<c:url value="/menuList" var="showMenuUrl" />
<button class="btn btn-info" onclick="location.href='${showMenuUrl}/1'">Show Menu</button>

</body>
</html>
