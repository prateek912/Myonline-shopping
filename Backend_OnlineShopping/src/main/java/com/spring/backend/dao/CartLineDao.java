package com.spring.backend.dao;

import java.util.List;

import com.spring.backend.dto.Cart;
import com.spring.backend.dto.CartLine;


public interface CartLineDao {
	
	// Common methods
	public CartLine getCartLineById(int id);
	public  boolean addCartLine(CartLine cartLine);
	public  boolean updateCartLine(CartLine cartLine);
	public  boolean deleteCartLine(CartLine cartLine);
	public List<CartLine> getList(int id);
	
	// other method
	public List<CartLine> listAvailable(int cartId);
	public CartLine getCartLineByProductIdAndCart(int cartId,int productId);
	public boolean udpateCart(Cart cart);
	
}
