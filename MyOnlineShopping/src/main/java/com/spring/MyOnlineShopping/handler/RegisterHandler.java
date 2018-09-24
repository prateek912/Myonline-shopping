package com.spring.MyOnlineShopping.handler;

import org.springframework.stereotype.Component;

import com.spring.MyOnlineShopping.model.RegisterModel;
import com.spring.backend.dto.Address;
import com.spring.backend.dto.User;

@Component
public class RegisterHandler {

	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel model, User user) {
		model.setUser(user);
	}
	
	public void addBilling(RegisterModel model, Address address) {
		model.setAddress(address);
	}
}
