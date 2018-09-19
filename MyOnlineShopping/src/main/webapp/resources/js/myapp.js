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
				
		default :
			$('#viewProducts').addClass('active');
		 	// For activating selected category from side bar
			$('#a_'+menu).addClass('active');
			break;	
				
	}
});

