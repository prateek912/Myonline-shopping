<%@ taglib prefix="spring-form"
	uri="http://www.springframework.org/tags/form"%>
<!-- include header -->
<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<h2>Confirmation</h2>
	<p>Please confirm your information before proceeding!</p>
	<hr />
		<div class="row">
			<!-- For Displaying User information  -->
			<div class="col-sm-5">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Personal Information</h4>
						<hr />
					</div>
					<div class="panel-body">
						<!-- Code to display user information -->
						<div class="text-cetner">
							<h4>${registerModel.user.firstName}
								${registerModel.user.lastName}</h4>
							<h5>${registerModel.user.email}</h5>
							<h5>${registerModel.user.contactNumber}</h5>
							<h5>Role : ${registerModel.user.role}</h5>
						</div>
					</div>
					<div class="panel-footer" align="center">
						<!-- Anchor tag to move to Edit personal information -->
						<a href="${flowExecutionUrl}&_eventId_personal"
							class="btn btn-warning">Edit</a>
					</div>
				</div>
			</div>
			<!-- For some space -->
			<div class="col-sm-2"></div>
			<!-- For Displaying User Billing Address Information  -->
			<div class="col-sm-5" >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Billing Information</h4>
						<hr />
					</div>
					<div class="panel-body">
						<!-- Code to display user billing address -->
						<div class="text-cetner">
							<h4>${registerModel.address.addresslineOne}</h4>
							<h5>${registerModel.address.addresslineTwo}</h5>
							<h5>${registerModel.address.city}-
								${registerModel.address.state} -
								${registerModel.address.country}</h5>
						</div>
					</div>
					<div class="panel-footer" align="center" style=" position : absolute; left :0; right:0;
																					bottom:0;">
						<a href="${flowExecutionUrl}&_eventId_billing"
							class="btn btn-warning">Edit</a>
					</div>
				</div>
			</div>
	</div>
	<!-- Confirm button after displaying both information -->
	<div class="row">
		<div class="col-sm-12 col-sm-offset-12">
			<div class="text-center" align="center">
				<a href="${flowExecutionUrl}&_eventId_submit"
					class="btn btn-primary">Confirm</a>
			</div>
		</div>
	</div>
</div>