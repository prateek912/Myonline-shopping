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
			//$('#viewProducts').addClass('active');
		 	// For activating selected category from side bar
			$('#a_'+menu).addClass('active');
			break;	
				
	}
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
						return '<span style="color : red">Product Not Available! </span>';
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
											'View</a>'+' ';
					if(window.quantity > 0){
						str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success">'+
						'Add To Cart</a>';
					}else{
						str += '<a href="'+window.contextRoot+'/cart/add/'+data+
						'/product" class="btn btn-success disabled">'+
						'Add To Cart</a>';
					}
					return str;
				}
			}
		]
	});
}


// To Close alert box in some time interval
var $alert = $('.alert');
if($alert.length){
	setTimeout(() => {
		$alert.fadeOut('slow');
	}, 3000);
}