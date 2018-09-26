package com.spring.MyOnlineShopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.MyOnlineShopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	// For logging
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@GetMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		ModelAndView mv = new ModelAndView("page");

		if (result != null) {
			switch (result) {

			case "updated":
				mv.addObject("message", "Cart has been updated successgully!!");
				break;

			case "error":
				mv.addObject("message", "Something went wrong!!");
				break;
			
			case "deleted" :
				mv.addObject("message", "Product removed successfully!!");
				break;
			
			case "added" :
				mv.addObject("message", "Product added successfully!!");
				break;
		
			case "unavailable" :
				mv.addObject("message", "Product Quantity not Available right now!!");
				break;
			
			}
		}

		mv.addObject("title", "User Cart");
		mv.addObject("userClickedShowCart", true);

		// Fetched cartLines for user
		logger.debug("Fetched cartlines :" + cartService.getCartLines());

		mv.addObject("cartLines", cartService.getCartLines());

		return mv;
	}

	@GetMapping("/{cartLineId}/update")
	public String updateCount(@PathVariable("cartLineId") int cartLineId, @RequestParam("count") int count) {
		String response = cartService.updateCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}
	
	// TO remove product from cart
	@GetMapping("/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable("cartLineId") int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}
	
	// To add product to cartline
	@GetMapping("add/{productId}/product")
	public String addProdutToCartLine(@PathVariable("productId") int productId) {
		String response = cartService.addProduct(productId);
		return "redirect:/cart/show?" + response;
	}
}
