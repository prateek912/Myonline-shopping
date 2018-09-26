package com.spring.backend.dao;

import java.util.List;

import com.spring.backend.dto.Address;
import com.spring.backend.dto.User;

public interface UserDao {

	public boolean addUser(User user);
	public User getUserByEmail(String email);
	public boolean addAddress(Address address);
	public Address getBillingAddress(User user);
	public List<Address> getListOfShippingAddress(User user);
	
}

