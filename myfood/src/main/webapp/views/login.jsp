<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") == null 
	&& session.getAttribute("userRole") == null	
	){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header2.jsp" />
<title>Home Page</title>
<script src="<c:url value="/resources/js/itemHelper.js" />"></script>
<script type="text/javascript">
function validate() {
	var email=document.getElementById('email').value;
	var password=document.getElementById('password').value;
	/* var a = document.getElementById('email');
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf("."); */
    /* if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    } */
    if(email == null || email == ''){
    	alert("Not a valid user Id");
    	return false;
    }
    if(password == null || password == ''){
    	alert("Not a valid password");
    	return false;
    }
    
    
    document.getElementById("myForm").submit();
}
</script>
</head>
<body>
	
    <c:url value="/login" var="userActionUrl" />
	<form action="${userActionUrl}" method="POST" class="form-horizontal" id="myForm">
	<p class="text-success">${errorMsg}</p>
        <div align="center">
            <table>
                <tr>
                    <td>User Name:</td>
                    <td><input type="text" name="email" id="email" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" id="password"  /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="button" value="Submit" onclick="validate();" /></td>
                </tr>
            </table>
            <div style="color: red">${error}</div>
        </div>
    </form>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/homePage");
	}
%>