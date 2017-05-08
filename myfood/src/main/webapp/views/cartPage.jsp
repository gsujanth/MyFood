<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if (session.getAttribute("customerId") != null 
	&& session.getAttribute("userRole") != null
	&& session.getAttribute("userRole").equals("user")){
%>
<html>
<head>
<title>Cart Page</title>
<jsp:include page="headerUser.jsp" />
</head>
<body>
<h1>MyFood Cart</h1>
<c:url value="/cart/checkOut" var="checkOutUrl" />
<c:url value="/deleteCartItem" var="deleteItemUrl" />
<c:choose>
<c:when test="${cartSize == 0}">
<h2>Your Cart is Empty!!</h2>
</c:when>
<c:otherwise>
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
	      <td><div>
		      <button type="button" class="btn btn-default" onclick="location.href='${deleteItemUrl}/${cartItem.itemId}'">
				<span class="glyphicon glyphicon-remove-circle"></span>
			  </button>
		  </div></td>
	   </tr>
	   </c:forEach>
	</table>
	<!-- Sum : <span id="sum">0</span> -->
	<button class="btn btn-info center-block" onclick="location.href='${checkOutUrl}'">Check Out</button>
	</c:otherwise>
</c:choose>
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
  updatedPrice = price*valueCurrent;
  $(this).parents('tr').find('input').eq(1).val(updatedPrice);
  sendViaAjax($(this).parents('tr').find('input').eq(3).val(), valueCurrent, updatedPrice);  
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
function sendViaAjax(itemId, valueCurrent, updatedPrice) {
	  var editItem = {}
	  editItem["itemId"] = itemId;
	  editItem["itemQunatity"] = valueCurrent;
	  editItem["updatedPrice"] = updatedPrice;
	 
	  $.ajax({
		  url: "/myfood/updateUserCart",
		  type : "POST",
		  contentType : "application/json",
		  data : JSON.stringify(editItem),
		  dataType : 'json',
		  headers: { 
			  'Accept': 'application/json',
			  'Content-Type': 'application/json' 
			  },
		  success: function (data) {},
		  error: function(textStatus, errorThrown){}
		});
}

</script>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("/myfood/views/login.jsp");
	}
%>