<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!------ Include the above in your HEAD tag ---------->
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<c:if test="${not empty message}">
		<div class="container">
			<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${not empty cartLines}">
			<div class="container">
				<table id="cart" class="table table-hover table-condensed">
					<thead>
						<tr>
							<th style="width: 50%">Product</th>
							<th style="width: 10%">Price</th>
							<th style="width: 8%">Quantity</th>
							<th style="width: 22%" class="text-center">Subtotal</th>
							<th style="width: 10%"></th>
						</tr>
					</thead>
					<tbody>

						<!-- For every cart line product -->
						<c:forEach items="${cartLines}" var="cartLine">
							<tr>
								<td data-th="Product">
									<div class="row">
										<div class="col-sm-2 hidden-xs">
											<img src="${image}/${cartLine.product.code}.jpg" alt="${cartLine.product.name}" 
												class="img-responsive cartImg" />
										</div>
										<div class="col-sm-10">
											<h4 class="nomargin">${cartLine.product.name}</h4>
											<hr>
											<p><strong>Brand</strong> - ${cartLine.product.brand}</p>
											<span>${cartLine.product.description}</span>
										</div>
									</div>
								</td>
								<td data-th="Price">&#8377; ${cartLine.product.unitprice} </td>
								<td data-th="Quantity"><input type="number" id="count_${cartLine.id}" min = "1"
									class="form-control text-center" value="${cartLine.productCount}"></td>
								<td data-th="Subtotal" class="text-center">&#8377;  ${cartLine.total}</td>
								<td class="actions" data-th="">
									<button type="button" class="btn btn-info btn-sm" name="refreshCart"
										value="${cartLine.id}">
										<i class="fa fa-refresh"></i>
									</button>
									<a href="${contextRoot}/cart/${cartLine.id}/delete" class="btn btn-danger btn-sm">
										<i class="fa fa-trash-o"></i>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr class="visible-xs">
							<td class="text-center"><strong>Total &#8377; ${userModel.cart.grandTotal}</strong></td>
						</tr>
						<tr>
							<td><a href="${contextRoot}/show/all/products" class="btn btn-warning"><i
									class="fa fa-angle-left"></i> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Total
									&#8377; ${userModel.cart.grandTotal}</strong></td>
							<td><a href="#" class="btn btn-success btn-block">Checkout
									<i class="fa fa-angle-right"></i>
							</a></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<div class="text-center">
					<h1>Your cart is empty!</h1>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>