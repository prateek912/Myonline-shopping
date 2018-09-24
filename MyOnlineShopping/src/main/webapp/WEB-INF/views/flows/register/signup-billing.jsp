<%@ taglib prefix="spring-form"
	uri="http://www.springframework.org/tags/form"%>
<!-- include header -->
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="com-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Billing Information</h4>
					<hr />
				</div>
				<div class="panel-body">
					<!-- form elements -->
					<spring-form:form class="form-horizontal" action="" method="post"
						modelAttribute="billing" id="billing_form">
						<!-- For User Address -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Address Line One </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="addresslineOne" id="addresslineOne"
										placeholder="Enter Your Locality!" class="form-control" />
									<spring-form:errors path="addresslineOne" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User Address -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Address Line Two </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="addresslineTwo" id="addresslineTwo"
										placeholder="Enter Your Locality!" class="form-control" />
									<spring-form:errors path="addresslineTwo" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User City -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									City Name </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="city" id="city"
										class="form-control" placeholder="Enter Your City Name!"/>
									<spring-form:errors path="city" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User State Name -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									State Name </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="state" id="state"
										class="form-control" placeholder="Enter Your State Name!"/>
									<spring-form:errors path="state" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For Country Name -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name"> Enter
									Country Name </label>
								<div class="col-md-8">
									<spring-form:input type="text" path="country" id="country"
										class="form-control" placeholder="Enter Your Country Name!"/>
									<spring-form:errors path="country" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For User Postal Code -->
						<div class="form-group">
							<div class="row">
								<label class="control-label col-md-4" for="name">
									Postal Code </label>
								<div class="col-md-8">
									<spring-form:input type="number" path="postalCode"
										id="postalCode" placeholder="Enter Your Locality Postal Code!"
										class="form-control" />
									<spring-form:errors path="postalCode" cssClass="help-block"
										element="em" />
								</div>
							</div>
						</div>
						<!-- For submission Button -->
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary" name="_eventId_personal">
									Previous - Personal<i class="fas fa-caret-left"></i>
								</button>
								<button type="submit" class="btn btn-primary" name="_eventId_confirm">
									Next -> Confirm<i class="far fa-angle-right"></i>
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