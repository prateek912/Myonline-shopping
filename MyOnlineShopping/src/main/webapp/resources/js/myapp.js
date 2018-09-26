$(function(){
	// For Making Active Menu
	switch(menu){
		case 'Contact Page':
			$('#contact').addClass('active');
			break;
			
		case 'About Page' :
			$('#about').addClass('active');
			break;
		
		case 'All Products' :
			$('#viewProducts').addClass('active');
			break;
		
		case 'Manage Products' :
			$('#manageProducts').addClass('active');
			break;
			
		default :
			// $('#viewProducts').addClass('active');
		 	// For activating selected category from side bar
			$('#a_'+menu).addClass('active');
			break;	
				
	}
});

// To tackle csrf token
var token = $("meta[name='_csrf']").attr('content');
var header = "X-CSRF-TOKEN";

// set the token and header for AJAX request
 $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
 });


// Code for JQuery data table

var $table = $('#productListTable');
// Execute below only where we have above mentioned table
if($table.length){
	
	// Creating WebService URL
	var jsonUrl = '';
	if(window.categoryId == ''){
		// Means User is asking for all the Products
		jsonUrl = window.contextRoot+'/json/data/all/products';
	}else{
		jsonUrl = window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
	}
	
	$table.DataTable({
		lengthMenu : [[3,5,10,-1],['3 Records','5 Records','10 Records','All Records']],
		pageLength : 5,
		
		// AJAX Call for calling WebService and getting data
		ajax:{
			url : jsonUrl,
			dataSrc : '',
		},
		// For Printing Column
		columns : [
			{
				data : 'code',
				bSortable : false,
				mRender : function(data,type,row){
					return '<img class="dataTableImg" src="'+window.contextRoot+'/resources/images/'+data+'.jpg" />';
				}
			},
			{
				data : 'name'
			},
			{
				data : 'brand'
			},
			{
				data : 'unitprice',
				mRender : function(data,type,row){
					return '&#8377; '+data;
				}
			},
			{
				data : 'quantity',
				mRender : function(data,type,row){
					window.quantity = data;
					if(data > 0){
						return data;
					}else{
						return '<span style="color : red">Out of Stock! </span>';
					}
				}
			},
			{
				data : 'id',
				bSortable : false,
				mRender : function(data,type,row){
					// URL to show the Product
					var str = '';
					str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary">'+
											'<i class="fa fa-eye"></i></a>'+' ';
					if(window.quantity > 0){
						if(userRole == 'ADMIN'){
							str +='<a href="'+window.contextRoot+'/manage/'+data+'/product"class="btn btn-warning">'+
							'<i class="fa fa-pencil"></i></a>';	
						}else{
							str +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success">'+
							'<i class="fa fa-shopping-cart"></i></a>';	
						}
					}else{
						if(userRole == 'ADMIN'){
							str +='<a href="'+window.contextRoot+'/manage/'+data+'/product"class="btn btn-warning">'+
							'<i class="fa fa-pencil"></i></a>';	
						}else{
							str +='<a href="'+window.contextRoot+'/cart/add/'+data+
							'/product" class="btn btn-success disabled">'+
							'<i class="fa fa-shopping-cart"></a>';
						}
					}
					return str;
				}
			}
		]
	});
}

// For ADMIN data table
var $adminTable = $('#adminProductTable');
// Execute below only where we have above mentioned table
if($adminTable.length){
	
	// Creating WebService URL
	var jsonUrl = window.contextRoot+'/json/data/admin/all/products';
	
	$adminTable.DataTable({
		lengthMenu : [[10,30,50-1],['10 Records','30 Records','50 Records','All Records']],
		pageLength : 10,
		
		// AJAX Call for calling WebService and getting data
		ajax:{
			url : jsonUrl,
			dataSrc : '',
		},
		// For Printing Column
		columns : [
			
			{
				data : 'id',
			},
			{
				data : 'code',
				bSortable : false,
				mRender : function(data,type,row){
					return '<img class="adminDataTableImg" src="'+window.contextRoot+'/resources/images/'+data+'.jpg" />';
				}
			},
			{
				data : 'name'
			},
			{
				data : 'brand'
			},
			{
				data : 'unitprice',
				mRender : function(data,type,row){
					return '&#8377; '+data;
				}
			},
			{
				data : 'quantity',
				mRender : function(data,type,row){
					window.quantity = data;
					if(data > 0){
						return data;
					}else{
						return '<span style="color : red">Out of Stock! </span>';
					}
				}
			},
			{
				data : 'active',
				bSortable : false,
				mRender : function(data,type,row){
					var str = '';
					if(data){
						str += '<lable class="switch"> <input '+
						'type="checkbox" checked="checked" value="'+row.id+'" />'+
							'</lable>';
					}else{
						str += '<lable class="switch"> <input '+
						'type="checkbox" value="'+row.id+'" />'+
							'</lable>';
					}
					return str;
				}
			},
			{
				data : 'id',
				bSortable : false,
				mRender : function(data,type,row){
					var str = '';
					str+= '<a href="'+window.contextRoot+'/manage/'+data+'/product"'+
						'class="btn btn-warning"><i class="fa fa-pencil"></i></a>';
					
					return str;
				}
			}
		],
		// So that BootBox alert load after this table populated
		initComplete : function(){
			// To make alert box on switch
			var api = this.api();
			api.$('.switch input[type="checkbox"]').on('change',function(){
				var checkbox = $(this);
				var checked = checkbox.prop('checked');
				
				var msg = (checked)? 'You want to activate Product?' :
												'You Sure want to deactivate the product?';
				var value = checkbox.prop('value');
				
				bootbox.confirm({
					size : 'medium',
					title : 'Product activation and Deactivation',
					message : msg,
					callback : function(confirmed){
						// Whether user has confirmed or not
						if(confirmed){
							// Now we have to make changes in database
							var activationUrl = window.contextRoot+'/manage/product/'+value+'/activation';
							$.post(activationUrl,function(data){
								bootbox.alert({
									size : 'medium',
									title : 'Product activation and Deactivation',
									message : data
								});
							});
						}else{
							checkbox.prop('checked',!checked);
						}
					}
				});
			});
		}
	});
}

// To Close alert box in some time interval
var $alert = $('.alert');
if($alert.length){
	setTimeout(() => {
		$alert.fadeOut('slow');
	}, 3000);
}


// For Validation of Admin Category Add form
var $cateogryForm = $('#cat_form');
if($cateogryForm.length){
	$cateogryForm.validate({
		rules: {
			name: {
				required: true,
				minlength: 3
			},
			description: {
				required: true,
				minlength: 3					
			}				
		},
		messages: {					
			name: {
				required: 'Please enter product name!',
				minlength: 'Please enter atleast three characters'
			},
			description: {
				required: 'Please enter product name!',
				minlength: 'Please enter atleast three characters'
			}					
		},
		
		errorElement : "em",
		errorPlacement : function(error, element) {
			// Add the 'help-block' class to the error element
			error.addClass("help-block");
			
			// add the error label after the input element
			error.insertAfter(element);
			
			
			// add the has-feedback class to the
			// parent div.validate in order to add icons to inputs
			element.parents(".validate").addClass("has-feedback");	

		}	
	});
}


// Validating Login Form
var $loginForm = $('#loginForm');
if($loginForm.length){
	$loginForm.validate({
		rules : {
			email : {
				required: true,
				email : true
			},
			password : {
				required: true
			}
		},
		messages: {					
			name: {
				required: 'Please enter email address!',
				email : 'Please enter a valid email address!'	
			},
			description: {
				required: 'Please enter password!'
			}					
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			// Add the 'help-block' class to the error element
			error.addClass("help-block");
			
			// add the error label after the input element
			error.insertAfter(element);
			
			
			// add the has-feedback class to the
			// parent div.validate in order to add icons to inputs
			element.parents(".validate").addClass("has-feedback");	

		}
	});
}


// Cart refresh button functionality
$('button[name="refreshCart"]').click(function(){
	
	//Fetch cartLine id
	var cartLineId = $(this).attr('value');
	var quantity = $('#count_'+cartLineId);
	
	// Get original count
	var originalCount = quantity.attr('value');
	var currentCount = quantity.val();

	// Only when count has been changed
	if(currentCount !== originalCount){
		
		if(currentCount < 1){
			// reverting it back to original count
			quantity.val(originalCount);
			
			// Giving user alert
			bootbox.alert({
				size : 'medium',
				title : 'Error',
				message : 'Quantity can not be less than 1'
			});
		}else{
			// Creating a new Url to hit update count controller method
			var updatedUrl = window.contextRoot+'/cart/'+cartLineId+'/update?count='+currentCount;
			console.log("Updated Url :"+updatedUrl);
			
			// Forward it to controller
			window.location.href = updatedUrl;
		}
		
		
	}
	
	
});