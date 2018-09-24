<%@ taglib prefix="spring-form"
	uri="http://www.springframework.org/tags/form"%>
<!-- include header -->
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="com-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Personal</h4>
					<hr />
				</div>
				<div class="panel-body">
					<!-- form elements -->
					<spring-form:form class="form-horizontal" action="" method="post"
						modelAttribute="user" id="signup_form">
						<!-- For User FIrst Name -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									First Name </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="firstName" id="firstName"
										placeholder="Enter Your First Name!" class="form-control" />
									<spring-form:errors path="firstName" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User last Name -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									Last Name </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="lastName" id="lastName"
										placeholder="Enter Your last Name!" class="form-control" />
									<spring-form:errors path="lastName" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User email -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									Email id </label>
								<div class="col-md-8">
									<spring-form:input type="email" path="email" id="email"
										class="form-control" placeholder="Enter Your personal Email id!"/>
									<spring-form:errors path="email" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User Contact Number -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									Mobile Number</label>
								<div class="col-md-8">
									<spring-form:input type="number" path="contactNumber"
										id="contactNumber"
										placeholder="Enter Your personal contact number!"
										class="form-control" />
									<spring-form:errors path="contactNumber" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For radio button -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name">Select
									Role </label>
								<div class="col-md-8">
									<label class="radio-inline">
										<spring-form:radiobutton path="role" value="USER" checked="checked"/>User
									</label>
									<label class="radio-inline">
										<spring-form:radiobutton path="role" value="SUPPLIER"/>Supplier
									</label>
								</div>
							</div>
						</div>
						<!-- For User Password -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name">
									Password </label>
								<div class="col-md-8">
									<spring-form:input type="password" path="password"
										id="password" placeholder="Enter Your desired password"
										class="form-control" />
									<spring-form:errors path="password" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For submission Button -->
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary" name="_eventId_billing">
									Next -> Billing<span class="glyphicon glyphicon-chevron-right"></span>
								</button>
								<hr />
							</div>
						</div>
					</spring-form:form>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<!-- include footer -->
<%@include file="../shared/flows-footer.jsp"%>
