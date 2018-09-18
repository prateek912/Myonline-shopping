package com.spring.MyOnlineShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
 
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page");
		mv.addObject("title", "Home Page");
		mv.addObject("userClickedHome",true);
		
		return mv;
	}
	
	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", "About Page");
		mv.addObject("userClickedAbout",true);
		
		return mv;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView();
		// Because we are showing about div on same page
		mv.setViewName("page");
		mv.addObject("title", "Contact Page");
		mv.addObject("userClickedContact",true);
		
		return mv;
	}
	
	
	
}
