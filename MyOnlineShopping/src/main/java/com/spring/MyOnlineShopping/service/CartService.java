package com.spring.MyOnlineShopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.MyOnlineShopping.model.UserModel;
import com.spring.backend.dao.CartLineDao;
import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.CartLine;
import com.spring.backend.dto.Product;

@Service("cartService")
public class CartService {

	// For logging
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartLineDao cartlineDao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProductDao productDao;
	
	private Cart cart = null;
	
	// Fetch the cart of user from session
	private Cart getCartFromService() {
		return ((UserModel) session.getAttribute("model")).getCart();
	}
	
	// Fetch cart from DB
	public List<CartLine> getCartLines(){
		cart = this.getCartFromService();
		logger.debug("Fetched Cart id :"+cart.getId());
		return cartlineDao.getList(cart.getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		
		// Fetch cart line from id
		CartLine cartLine = cartlineDao.getCartLineById(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}else {
			// Get product
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			
			// If available quantity is less than count
			if(product.getQuantity() < count ) {
				return "result=unavailable";
			}
			
			// Set cartline product count
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setTotal(product.getUnitprice()*count);
			
			// update cartline
			cartlineDao.updateCartLine(cartLine);
			
			// update cart
			Cart cart = this.getCartFromService();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartlineDao.udpateCart(cart);
			
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		// Fetch cartline 
		CartLine cartLine = cartlineDao.getCartLineById(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}else {
			// First cart
			Cart cart = this.getCartFromService();
			
			//update grandtotal
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() -1);
			
			// call our dao 
			cartlineDao.udpateCart(cart);
			
			// remove the cart line
			cartlineDao.deleteCartLine(cartLine);
			
			return "result=deleted";
		}
		
	}

	public String addProduct(int productId) {
		
		String response = null;
		
		// To make sure product is not already added
		Cart cart = this.getCartFromService();
		CartLine cartLine = cartlineDao.getCartLineByProductIdAndCart(cart.getId(), productId);
		
		if(cartLine == null) {
			// add a new cart Line
			cartLine = new CartLine();
			Product product = productDao.getProductById(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setProduct(product);
			// only adding 1 product at a time
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitprice());
			cartLine.setAvailable(true);
			
			cartlineDao.addCartLine(cartLine);
			
			// Updating Grand total
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartlineDao.udpateCart(cart);
			
			response = "result=added";
		}else {
			// If product is already present in cart then increase its quantity
			response = this.updateCartLine(cartLine.getId(),cartLine.getProductCount() +1);
		}
		
		return response;
	}
}
