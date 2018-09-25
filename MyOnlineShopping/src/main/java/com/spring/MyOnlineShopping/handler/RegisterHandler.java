package com.spring.MyOnlineShopping.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.MyOnlineShopping.model.RegisterModel;
import com.spring.backend.dao.UserDao;
import com.spring.backend.dto.Address;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.User;

@Component
public class RegisterHandler {

	// For logging 
	private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);
	
	@Autowired
	private UserDao userDao;
	
	// For password encoding
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel model, User user) {
		model.setUser(user);
	}
	
	public void addBilling(RegisterModel model, Address address) {
		model.setAddress(address);
	}
	
	// Method to save user and address
	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		
		// Fetch the user
		User newUser = model.getUser();
		
		// If role is USER then create Cart for User
		logger.debug("Role is :"+newUser.getRole());
		if(newUser.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(newUser);
			newUser.setCart(cart);
		}
		
		// Encoding password before saving it to database
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		// save the user
		userDao.addUser(newUser);
		
		// Fetch address
		Address billing = model.getAddress();
		billing.setUser(newUser);
		billing.setBilling(true);
		userDao.addAddress(billing);
		
		return transitionValue;
		
	}
	
	// For validating user entered email id and password match
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		
		// For matching Password
		logger.info("Validation for matching Password and Confirm password");
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			transitionValue = "failure";
			error.addMessage(new MessageBuilder()
					.error().source("confirmPassword")
						.defaultText("Password did not Match!! Please try again!!")
							.build());
		}
		
		// For uniqueness of email id
		logger.info("Validation for Uniqieness of user entered email :"+user.getEmail());
		if(userDao.getUserByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder()
					.error().source("email")
						.defaultText("Email id already taken, please entry some other email id!!")
							.build());
		}
		
		return transitionValue;
	}
}
