package com.spring.MyOnlineShopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView NoHandlerFoundExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("errorTitle", "Wrong URL, no page found!!");
		mv.addObject("errorDescription", "URL Seems to be incorrect, please check it again");
		mv.addObject("title", "404 Not Found");

		return mv;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView ProductNotFoundExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("errorTitle", "Wrong Product Id selected!!");
		mv.addObject("errorDescription", "No Such Product found!!");
		mv.addObject("title", "404 Product Found");

		return mv;
	}

	// For handling General Exceptions
	@ExceptionHandler(Exception.class)
	public ModelAndView GeneralExceptionHandler(Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("errorTitle", "Something went wrong, please try again!!");

		// For Debugging Application
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		ex.printStackTrace(pw);

		mv.addObject("errorDescription", sw.toString());
		mv.addObject("title", "Contact Your Administrator");

		return mv;
	}

}
