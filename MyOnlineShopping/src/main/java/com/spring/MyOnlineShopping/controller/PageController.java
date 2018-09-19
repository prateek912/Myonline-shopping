package com.spring.MyOnlineShopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dto.Category;

@Controller
public class PageController {

	@Autowired
	private CategoryDao catDao;
	
	// Object that will contain all the categories
	private List<Category> catlist = new ArrayList<>();
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page");
		mv.addObject("title", "Home Page");
		mv.addObject("userClickedHome", true);

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

}
