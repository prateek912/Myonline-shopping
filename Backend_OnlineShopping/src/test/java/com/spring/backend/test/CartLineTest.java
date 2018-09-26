package com.spring.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.backend.dao.CartLineDao;
import com.spring.backend.dao.ProductDao;
import com.spring.backend.dao.UserDao;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.CartLine;
import com.spring.backend.dto.Product;
import com.spring.backend.dto.User;


public class CartLineTest {

	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDao cartLineDao = null;
	private static ProductDao productDao = null;
	private static UserDao userDao = null;
	
	private static Product product = null;
	private static User user= null;
	private static  Cart cart= null;
	private static CartLine cartLine= null;
	
	
	@BeforeClass
	public static void inti() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.backend");
		context.refresh();

		userDao = context.getBean(UserDao.class);
		productDao = context.getBean(ProductDao.class);
		cartLineDao = context.getBean(CartLineDao.class);
	}
	
	@Test
	public void testAddCartLine() {
		// Fetch the user
		user = userDao.getUserByEmail("swapnil@gmail.com");
		
		// Fetch the cart
		cart = user.getCart();
		
		// Fetch the product
		product = productDao.getProductById(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		
		assertEquals("Falied while adding Cartline!!",true,cartLineDao.addCartLine(cartLine));
		
		// update cart test
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Falied to update cart!!",true,cartLineDao.udpateCart(cart));
	}
	
	
}
