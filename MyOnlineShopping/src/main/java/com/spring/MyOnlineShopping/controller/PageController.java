package com.spring.MyOnlineShopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(name="error",required=false) String error, 
				@RequestParam(value="logout",required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		if(error !=null) {
			mv.addObject("message","Invalid UserName and Password!!");
		}
		
		if(logout != null) {
			mv.addObject("logout","You have succesfully logged out!!");
		}
		mv.addObject("title","login");
		return mv;
	}

	// Access Denied Mapping
	@RequestMapping("/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("title","403 - Access Denied");
		mv.addObject("errorTitle","Aha! Caught you");
		mv.addObject("errorDescription","You are not authorized to view this page!");
		
		return mv;
	}

	
	@RequestMapping("/perform-Logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		// First we will fetch the authentication object
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			//logout
			new SecurityContextLogoutHandler().logout(request, response, auth);
			
			if(SecurityContextHolder.getContext().getAuthentication() == null) {
				logger.info("Successfully logged out user!!");
			}else {
				logger.info("Unsuccessfully logged out user!!");
			}
		}
		
		return "redirect:/login?logout";
	}
}
