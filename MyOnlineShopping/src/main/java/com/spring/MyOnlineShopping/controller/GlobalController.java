package com.spring.MyOnlineShopping.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.MyOnlineShopping.model.UserModel;
import com.spring.backend.dao.UserDao;
import com.spring.backend.dto.User;

@ControllerAdvice
public class GlobalController {
	
	// For logging
	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private HttpSession session;
	
	private UserModel userModel = null; 
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("model") == null) {
			// have to add userModel to user
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// as our UserName is email in login form
			logger.info("Checking User for......"+auth.getName());
			User user = userDao.getUserByEmail(auth.getName());
			
			if(user != null) {
				// Create a new user model to pass user detail
				userModel = new UserModel();
				userModel.setEmail(user.getEmail());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				userModel.setId(user.getId());
				userModel.setRole(user.getRole());
				
				// Setting cart only if User role is USER
				if(userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}
				
				// setting userModel in session
				session.setAttribute("model",userModel);
				
				return userModel;
			}
		}else {
			logger.info("User session is still active :"+userModel.getEmail());
		}
		
		return (UserModel) session.getAttribute("model");
	}
}
