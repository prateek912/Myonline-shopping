<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring-form"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- Admin Form for adding new Product or Editing existing one -->
		<div class="row">
			<div class="com-md-offset-2 col-md-8">
				<!-- For Success Alert -->
				<c:if test="${not empty message}">
					<c:choose>
						<c:when test="${fn:containsIgnoreCase(message,'Wrong')}">
							<div class="col-xs-12">
								<div class="alert alert-danger alert-dismissible">
									<button type="button" class="close" data-dimiss="alert">
										&times;</button>
									${message}
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-xs-12">
								<div class="alert alert-success alert-dismissible">
									<button type="button" class="close" data-dimiss="alert">
										&times;</button>
									${message}
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Product Management</h4>
						<hr />
					</div>
					<div class="panel-body">
						<!-- form elements -->
						<spring-form:form class="form-horizontal" modelAttribute="product"
							action="${contextRoot}/manage/products" method="post"
							enctype="multipart/form-data">
							<!-- For Product Name -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name"> Enter
										Product Name </label>
									<div class="col-md-8">
										<spring-form:input type="text" path="name" id="name"
											placeholder="Enter Product Name!" class="form-control" />
										<spring-form:errors path="name" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Brand Name -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name"> Enter
										Brand Name </label>
									<div class="col-md-8">
										<spring-form:input type="text" path="brand" id="brand"
											placeholder="Enter Brand Name!" class="form-control" />
										<spring-form:errors path="brand" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Product Description -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name"> Enter
										Product Description </label>
									<div class="col-md-8">
										<spring-form:textarea rows="4" cols="50" path="description"
											id="description" class="form-control"></spring-form:textarea>
										<spring-form:errors path="description" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Product Price -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name"> Enter
										Product Price </label>
									<div class="col-md-8">
										<spring-form:input type="number" path="unitprice"
											id="unitprice" placeholder="Price in Rupees "
											class="form-control" />
										<spring-form:errors path="unitprice" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Quantity -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name">
										Available Quantity </label>
									<div class="col-md-8">
										<spring-form:input type="number" path="quantity" id="quantity"
											placeholder="Enter Available Quantity of Product"
											class="form-control" />
										<spring-form:errors path="quantity" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Image  -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4"> Select an Image
									</label>
									<div class="col-md-8">
										<spring-form:input type="file" path="file" id="file"
											placeholder="Upload image of the Product"
											class="form-control" />
										<spring-form:errors path="file" cssClass="help-block"
											element="em" />
									</div>
								</div>
							</div>
							<!-- For Categories -->
							<div class="form-group">
								<div class="row">
									<label class="control-label col-md-4" for="name">
										Select Category </label>
									<div class="col-md-8">
										<spring-form:select path="categoryid" id="categoryid"
											class="form-control" items="${categories}" itemLabel="name"
											itemValue="id" />

										<!-- Show this Add Category Button, only for new product -->
										<c:if test="${product.id == 0}">
											<div class="text-right">
												<br />
												<button type="button" data-toggle="modal"
													data-target="#myCategoryModal"
													class="btn btn-warning btn-xs">Add New Category</button>
											</div>

										</c:if>
									</div>
								</div>
							</div>
							<!-- For submission Button -->
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" name="submit" id="submit" value="Submit"
										class="btn btn-primary" />
									<hr />
								</div>
							</div>
							<!-- Hidden Filed to populated form again when click on edit -->
							<spring-form:hidden path="id" />
							<spring-form:hidden path="code" />
							<spring-form:hidden path="supplierId" />
							<spring-form:hidden path="views" />
							<spring-form:hidden path="active" />
							<spring-form:hidden path="purchases" />

						</spring-form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- Admin table where admin can view all the product that are presernt in Database -->
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<h3>Available Products</h3>
					<hr />
				</div>
				<div class="row">
					<div style="overflow: auto">
						<table id="adminProductTable"
							class="table table-stripped table-bordered">
							<thead>
								<tr>
									<th>Id</th>
									<th>&#160;</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Active</th>
									<th>Edit</th>
								</tr>
							</thead>
							<!-- Table Body -->
							<!-- Will be filled by JS -->
							<tfoot>
								<tr>
									<th>Id</th>
									<th>&#160;</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Active</th>
									<th>Edit</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>

		<!-- Dialog box for adding new category -->
		<div class="modal fade" id="myCategoryModal" role="dialog"
			tabindex="-1" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						
						<h4 class="modal-title">Add New Category</h4>
					</div>
					<div class="modal-body">
						<!-- Category form -->
						<spring-form:form modelAttribute="category"
							action="${contextRoot}/manage/category" method="post"
							class="form-horizontal" id="cat_form">

							<div class="form-group">
								<label class="control-label col-md-4">Name</label>
								<div class="col-md-8 validate">
									<spring-form:input type="text" path="name" class="form-control"
										placeholder="Category Name" id="cat_name"/>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4">Description</label>
								<div class="col-md-8 validate">
									<spring-form:textarea path="description" class="form-control"
										placeholder="Enter category description here!" id="cat_description"/>
								</div>
							</div>


							<div class="form-group">
								<div class="col-md-offset-4 col-md-4">
									<input type="submit" name="submit" value="Save"
										class="btn btn-primary" />
								</div>
							</div>
						</spring-form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>