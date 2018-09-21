package com.spring.MyOnlineShopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.backend.dto.Product;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// Checking if the ValiDator is for Product class only
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		
		// Whether file has been selected or not
		if(product.getFile() == null || 
					product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file",null,"Please select image file to upload");
		}
		
		// To make sure only image file extensions
		
		if(!(product.getFile().getContentType().equals("image/jpeg") || 
				product.getFile().getContentType().equals("image/png") ||
						product.getFile().getContentType().equals("image/gif")	
			)){
			errors.rejectValue("file",null,"Select Image file only like .jpg .jpeg .png .gif ..");
		}
	}

}
