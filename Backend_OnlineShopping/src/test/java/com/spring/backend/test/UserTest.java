package com.spring.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.backend.dao.UserDao;
import com.spring.backend.dto.Address;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.User;

public class UserTest {

	private static AnnotationConfigApplicationContext context;
	private static UserDao userDao;
	private static User user;
	private static Address address;
	private static Cart cart;

	@BeforeClass
	public static void inti() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.backend");
		context.refresh();

		userDao = context.getBean(UserDao.class);
	}

	/*
	 * Testing all the CRUD operation related to User class
	 * 
	 */

	@Test
	@Ignore
	public void testUserCRUDOpetations() {

		// Testing add user
		user = new User();
		user.setFirstName("Pratik");
		user.setLastName("sharma");
		user.setContactNumber("8826545723");
		user.setEmail("sharma.prateek912@gmail.com");
		user.setPassword("abc@7898");
		user.setRole("USER");

		if (user.getRole().equals("USER")) {
			// We will be creating cart for this user
			cart = new Cart();
			cart.setUser(user);

			// linking cart to the user
			// Not saving cart separately
			user.setCart(cart);
			assertEquals("Failed while adding User!", true, userDao.addUser(user));
		}
	}

	// For test update cart method
	@Test
	@Ignore
	public void testUpdateCart() {
		// Fetch user by email
		user = userDao.getUserByEmail("sharma.prateek912@gmail.com");
		// fetch cart
		cart = user.getCart();
		cart.setGrandTotal(550.0);
		cart.setCartLines(2);

		assertEquals("Failed to update the cart!", true, userDao.udpateCart(cart));
	}

	// For testing Many to one relation of User with Address
	@Test
	@Ignore
	public void testAddingAddress() {
		// add user
		user = new User();
		user.setFirstName("Pratik");
		user.setLastName("sharma");
		user.setContactNumber("8826545723");
		user.setEmail("sharma.prateek912@gmail.com");
		user.setPassword("abc@7898");
		user.setRole("USER");

		assertEquals("Failed while adding User!", true, userDao.addUser(user));

		// Adding billing address
		address = new Address();
		address.setAddresslineOne("Sector 23 Gurgoan");
		address.setAddresslineTwo("Near Community Market");
		address.setBilling(true);
		address.setCity("Gurgoan");
		address.setCountry("India");
		address.setPostalCode("460661");
		address.setUser(user);
		address.setState("Haryana");

		assertEquals("Failed to add billing address", true, userDao.addAddress(address));

		// adding shipping address
		address = new Address();
		address.setAddresslineOne("Sector 71 Noida");
		address.setAddresslineTwo("Near HP Petrol Pump");
		address.setShipping(true);
		address.setCity("Nodia");
		address.setCountry("India");
		address.setPostalCode("201301");
		address.setUser(user);
		address.setState("Uttar Pradesh");

		assertEquals("Failed to add shipping address", true, userDao.addAddress(address));

	}

	// Testing fetch address of user
	@Test
	@Ignore
	public void testFetchAddress() {
		user = userDao.getUserByEmail("sharma.prateek912@gmail.com");

		// adding shipping address
		address = new Address();
		address.setAddresslineOne("Sector 61 Noida");
		address.setAddresslineTwo("Near SBI Bank");
		address.setShipping(true);
		address.setCity("Nodia");
		address.setCountry("India");
		address.setPostalCode("201301");
		address.setUser(user);
		address.setState("Uttar Pradesh");
		
		assertEquals("Failed to add shipping address", true, userDao.addAddress(address));
		
		// For billing address
		assertEquals("Failed to fetch billing address!!","Gurgoan",userDao.getBillingAddress(user).getCity());
		// For shipping address
		assertEquals("Failed to fetch billing address!!",2,userDao.getListOfShippingAddress(user).size());
	}
}
