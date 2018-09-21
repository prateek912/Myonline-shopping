package com.spring.MyOnlineShopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Category;
import com.spring.backend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	// For logging
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	private CategoryDao catDao;
	@Autowired
	private ProductDao productDao;
	
	// Fetching categories to be filled up in drop down in admin form
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return catDao.getList();
	}
	
	@GetMapping("/products")
	public ModelAndView showManageProduct(@RequestParam(name="operation",required=false) String operation) {
		logger.info("Mapped to Managed Product URL!!");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page");
		mv.addObject("userClickedManage", true);
		mv.addObject("title","Manage Products");
		
		// Adding a new Product thorough admin Console
		Product newProduct = new Product();
		newProduct.setSupplierId(1);
		newProduct.setActive(true);
		mv.addObject("product",newProduct);
		
		if(operation !=null) {
			// For Sending alert For Successful Addition to DB
			if(operation.equals("Product")) {
				mv.addObject("message","Product Added Successfully");
			}
		}
		
		return mv;
	}
	
	// For Handling product submission and mapping form field to Product Class
	@PostMapping("/products")
	public String productSubmission(@ModelAttribute("product") Product newPorduct) {
		
		// Create a new product record in database
		productDao.addProduct(newPorduct);
		logger.info("New Added Product from Admin Console :"+newPorduct.toString());
		return "redirect:/manage/products?operation=Product";
	}
}
