<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Application Landing Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> -->

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/navbar-fixed-top.css" />" rel="stylesheet">
    <style>
	    li {
		    display:inline;
		    padding: 5px;
	    }
    </style>
  </head>

  <body>
  <%request.getSession().setAttribute("usedId", 2);%>
  <c:url value="/editUserProfile/${usedId}" var="userUrl" />
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
		  <img src="<c:url value="/resources/images/myfood.png" />" style="width:180px;height:50px;">
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li>
            	<button type="submit" class="btn btn-default btn-lg" onclick="location.href='${userUrl}'">
					<span class="glyphicon glyphicon-user"></span>  Profile
				</button>
            </li>
            <li>
	            <button type="button" class="btn btn-default btn-lg">
					<span class="glyphicon glyphicon-shopping-cart"></span>  Cart
				</button>
			</li>
			<li><form action="">
            	<button type="submit" class="btn btn-default btn-lg">Sign Out</button>
            </form></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

   

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
    <!-- <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script> -->
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
  </body>
</html>