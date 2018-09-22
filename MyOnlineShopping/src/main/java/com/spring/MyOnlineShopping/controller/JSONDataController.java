package com.spring.MyOnlineShopping.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Product;

@RestController
@RequestMapping("/json/data")
public class JSONDataController {

	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/all/products")
	public List<Product> getAllProduct(){
		return productDao.getListOfActiveProducts();
	}
	
	@RequestMapping("/category/{id}/products")
	public List<Product> getProduct(@PathVariable("id") int id) {
		return productDao.getListOfActiveProductsByCategory(id);
	}
	
	@RequestMapping("/admin/all/products")
	public List<Product> getAlAdminlProduct(){
		return productDao.getAllProducts();
	}
	
}
