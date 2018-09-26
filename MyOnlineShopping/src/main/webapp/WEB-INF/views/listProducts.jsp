<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="topDiv">
			<div class="row">
				<!-- First Column -->
				<!-- To display side bar -->
				<div class="col-lg-3">
					<%@include file="./shared/side-bar.jsp"%>
				</div>

				<!-- Second Column -->
				<!-- To display Product -->
				<div class="col-lg-9">
					<!-- Adding BreadCrumb -->
					<div class="row">
						<div class="col-lg-12">
							<!-- If Clicked on All Product then breadCrumb -->
							<c:if test="${userClickedAllProducts == true}">
								<script type="text/javascript">
									// Storing categoryId in global variable
									/*
									 *   This variable will be only set when user click View Product
									 */
									// As no id will be selected here
									window.categoryId = '';
								</script>
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
									<li class="breadcrumb-item active">All Products</li>
								</ol>
							</c:if>
							<!-- If Clicked on particular category Product then breadCrumb -->
							<c:if test="${userClickedCatProducts == true}">
								<script type="text/javascript">
									// Storing categoryId in global variable
									/*
									 *   This variable will be only set when user click View Product
									 */
									window.categoryId = '${cat.id}';
								</script>
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
									<li class="breadcrumb-item active">Category</li>
									<li class="breadcrumb-item active">${cat.name}</li>
								</ol>
							</c:if>
						</div>
					</div>
					<!-- Actual Dynamic Product -->
					<div class="row">
						<div class="col-lg-12">
							<div class="container-fluid">
								<div class="table-responsive">
									<table id="productListTable"
										class="table table-striped table-border">
										<thead>
											<tr>
												<th></th>
												<th>Name</th>
												<th>Brand</th>
												<th>Price</th>
												<th>Quantity Left</th>
												<th></th>
											</tr>
										</thead>
										<!-- Body will be filled by JQuery Data Table -->
										<tfoot>
											<tr>
												<th></th>
												<th>Name</th>
												<th>Brand</th>
												<th>Price</th>
												<th>Quantity Left</th>
												<th></th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>