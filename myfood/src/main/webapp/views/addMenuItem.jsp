<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add Menu Item</title>
<jsp:include page="headerRestaurantOwner.jsp" />
<script type="text/javascript">
	function validate() {
		var itemId = document.myform.itemId.value;
		var restaurantId = document.myform.restaurantId.value;
		var itemName = document.myform.itemName.value;
		var category = document.myform.category.value;
		var cost = document.myform.cost.value;
		var calories = document.myform.calories.value;

		var errorMessage = '';
		var validate = true;

		if (itemId == 0 || isNaN(itemId)) {
			errorMessage = errorMessage + 'ItemId missing.';
			alert("Item Id missing.");
			return false;
		}
		if (restaurantId == 0 || isNaN(restaurantId)) {
			errorMessage = errorMessage + 'Restaurant Id missing.';
			alert("Restaurant Id missing.");
			return false;
		}
		if (itemName == null || itemName == '') {
			errorMessage = errorMessage + 'Item Name missing.';
			alert("Item Name missing.");
			return false;
		}
		if (category == null || category == '') {
			errorMessage = errorMessage + 'Category missing.';
			alert("Category missing.");
			return false;
		}
		if (cost == 0 || isNaN(cost)) {
			errorMessage = errorMessage + 'Cost missing.';
			alert("Cost Invalid.");
			return false;
		}
		if (calories == 0 || isNaN(calories)) {
			errorMessage = errorMessage + 'Calories missing.';
			alert("Calories Invalid.");
			return false;
		}
		
		document.getElementById('errorMsg').value = errorMessage;
	}
</script>
</head>
<body>

	<h4>Please enter the details of new menu item:</h4>
	<p class="text-success">${SuccessMsg}</p>
	<h3 class="text-danger" id="errorMessage">${errorMsg}</h3>

	<c:url value="/addMenuItem" var="userActionUrl" />
	<form:form name="myform" action="${userActionUrl}" method="POST"
		modelAttribute="menuitem" class="form-horizontal"
		onsubmit="return validate();">
		<div class="form-group">
			<label class="col-sm-2 control-label">Item Id</label>
			<div class="col-sm-10">
				<form:input path="itemId" type="number" class="form-control"
					name="itemId" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Restaurant Id</label>
			<div class="col-sm-10">
				<form:input path="restaurantId" type="number" class="form-control"
					name="restaurantId" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Item Name</label>
			<div class="col-sm-10">
				<form:input path="itemName" type="text" class="form-control"
					name="itemName" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Category</label>
			<div class="col-sm-10">
				<form:input path="category" type="text" class="form-control"
					name="category" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Cost</label>
			<div class="col-sm-10">
				<form:input path="cost" type="number" class="form-control"
					name="cost" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Calories</label>
			<div class="col-sm-10">
				<form:input path="calories" type="number" class="form-control"
					name="calories" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Add Item</button>
			</div>
		</div>
	</form:form>

</body>
</html>
