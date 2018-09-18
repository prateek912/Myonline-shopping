$(function(){
	// For Making Active Menu 
	switch(menu){
		case 'Contact Page':
			$('#contact').addClass('active');
			break;
			
		case 'About Page' :
			$('#about').addClass('active');
			break;
			
		default :
			$('#home').addClass('active');
			break;
				
	}
});