package com.spring.MyOnlineShopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.MyOnlineShopping.exception.ProductNotFoundException;
import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Category;
import com.spring.backend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDao catDao;
	@Autowired
	private ProductDao productDao;
	
	// Object that will contain all the categories
	private List<Category> catlist = new ArrayList<>();
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page");
		mv.addObject("title", "Home Page");
		mv.addObject("userClickedHome", true);

		// Logging
		logger.info("Successfully Mapped to /home /index - INFO");
		logger.debug("Successfully Mapped to /home /index - DEBUG");
		
		// Passing list of Category to this page
		catlist=catDao.getList();
		mv.addObject("catlist",catlist);
		
		// Setting this in session so that we dont have to fetch it again and again
		

		return mv;
	}

	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", "About Page");
		mv.addObject("userClickedAbout", true);

		return mv;
	}

	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView();
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", "Contact Page");
		mv.addObject("userClickedContact", true);

		return mv;
	}

	// Method to load all the products
	@RequestMapping("/show/all/products")
	public ModelAndView showAllProduct() {
		ModelAndView mv = new ModelAndView();
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickedAllProducts", true);
		// Passing list of Category to this page
		mv.addObject("catlist",catlist);

		return mv;
	}

	// Method to load all the products based on category
	@RequestMapping("/show/category/{id}/products")
	public ModelAndView showCatProduct(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();
		// Passing list of Category to this page
		mv.addObject("catlist",catlist);

		// Using CategoryDaoImpl to fetch single category
		Category cat = catDao.getById(id);
		// Passing this object
		mv.addObject("cat", cat);
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", cat.getName());
		mv.addObject("userClickedCatProducts", true);

		return mv;
	}
	
	// For viewing single Product
	@RequestMapping("/show/{id}/product")
	public ModelAndView showSingleProductPage(@PathVariable("id") int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		
		// First is to get Product
		Product product = productDao.getProductById(id);
		if(product == null) throw new ProductNotFoundException();
		
		// increment view count
		product.setViews(product.getViews()+1);
		// and update that product
		productDao.updateProduct(product);
		
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickedShowProduct",true);
		
		return mv;
	}
	
	

}
