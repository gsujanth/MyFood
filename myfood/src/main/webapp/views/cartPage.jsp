<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Cart Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>
<h1>MyFood Cart</h1>
<c:url value="/cart/checkout" var="checkOutUrl" />
	<table class="table noborder table-hover">
	   <tr>
			<td>Item</td>
			<td>Quantity</td>
			<td>Price</td>
			<td></td>
			<td></td>
	   </tr>
	   <c:forEach items="${cartItems}" var="cartItem" varStatus="itemCount">
	   <tr>
	      <td>${cartItem.itemName}</td>
	      <td>
	      	<div class="col-lg-4">
				  <div class="input-group">
			          <span class="input-group-btn">
			              <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[${itemCount.index}]">
			                  <span class="glyphicon glyphicon-minus"></span>
			              </button>
			          </span>
			          <input type="text" name="quant[${itemCount.index}]" class="form-control input-number" value="${cartItem.itemQuantity}" min="1" max="10">
			          <span class="input-group-btn">
			              <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[${itemCount.index}]">
			                  <span class="glyphicon glyphicon-plus"></span>
			              </button>
			          </span>
				  </div>
			</div>
	      </td>
	      <td>
	      <input type="text" class="form-control" id="price" value="${cartItem.itemCost}" disabled>
	      </td>
	      <td><input type="hidden" value="${cartItem.itemCost}"></td>
	      <td><input type="hidden" value="${cartItem.itemId}"></td>
	   </tr>
	   </c:forEach>
	</table>
	Sum : <span id="sum">0</span>
	<button class="btn btn-info center-block" onclick="location.href='${checkOutUrl}'">Check Out</button>
<script type="text/javascript">
$(document).ready(function(){
	//iterate through each input-number and add keyup
	//handler to trigger sum event
	$('.input-number').each(function() {

		$(this).keyup(function(){
			calculateSum();
		});
	});

});
function calculateSum() {
	var sum = 0;
	//iterate through each textboxes and add the values
	$('.input-number').each(function() {

		//add only if the value is number
		if(!isNaN(this.value) && this.value.length!=0) {
			sum += parseFloat(this.value);
		}

	});
	$("#sum").html(sum.toFixed(2));
}
</script>
<script type="text/javascript">
$('.btn-number').click(function(e){
  e.preventDefault();
  
  fieldName = $(this).attr('data-field');
  type      = $(this).attr('data-type');
  var input = $("input[name='"+fieldName+"']");
  var currentVal = parseInt(input.val());
  if (!isNaN(currentVal)) {
      if(type == 'minus') {
          
          if(currentVal > input.attr('min')) {
              input.val(currentVal - 1).change();
          } 
          if(parseInt(input.val()) == input.attr('min')) {
              $(this).attr('disabled', true);
          }

      } else if(type == 'plus') {

          if(currentVal < input.attr('max')) {
              input.val(currentVal + 1).change();
          }
          if(parseInt(input.val()) == input.attr('max')) {
              $(this).attr('disabled', true);
          }

      }
  } else {
      input.val(0);
  }
});
$('.input-number').focusin(function(){
 $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function() {
  
  minValue =  parseInt($(this).attr('min'));
  maxValue =  parseInt($(this).attr('max'));
  valueCurrent = parseInt($(this).val());
  
  name = $(this).attr('name');
  if(valueCurrent >= minValue) {
	 $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
  } else {
      alert('Sorry, the minimum value was reached');
      $(this).val($(this).data('oldValue'));
  }
  if(valueCurrent <= maxValue) {
	 $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
  } else {
      alert('Sorry, the maximum value was reached');
      $(this).val($(this).data('oldValue'));
  }
  price = $(this).parents('tr').find('input').eq(2).val();
  $(this).parents('tr').find('input').eq(1).val(price*valueCurrent);
  alert(valueCurrent);
  $.ajax({
	  url: 'updateUserCart',
	  type: 'GET',
	  async: false,
	  dataType: 'text',
	  processData: false,    
	  data: 'item=' +valueCurrent,
	  success: function (data) {
		  alert('success'); 
	  },
	  error: function(textStatus, errorThrown){
		    alert('Failure: ' + textStatus + ". Error thrown: " + errorThrown);
	  }
	});
});
$(".input-number").keydown(function (e) {
      // Allow: backspace, delete, tab, escape, enter and .
      if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
           // Allow: Ctrl+A
          (e.keyCode == 65 && e.ctrlKey === true) || 
           // Allow: home, end, left, right
          (e.keyCode >= 35 && e.keyCode <= 39)) {
               // let it happen, don't do anything
               return;
      }
      // Ensure that it is a number and stop the keypress
      if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
          e.preventDefault();
      }
  });
</script>
</body>
</html>