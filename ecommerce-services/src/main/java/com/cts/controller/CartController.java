package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Cart;
import com.cts.response.Response;
import com.cts.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("save-cart")
	public Response saveCart(@RequestBody Cart cart) {
		return cartService.saveCart(cart);
	}

	@GetMapping("get-particular-cart")
	public Response getCartParam(@RequestParam Integer cartId) {
		return cartService.getCart(cartId);
	}

	@GetMapping("get-particular-user")
	public List<Cart> getCartItemsByUserIdParam(@RequestParam Integer userId) {
		return cartService.getCartItemsByUserId(userId);
	}

	@GetMapping("get-all-cart")
	public List<Cart> getAllCart() {
		return cartService.getAllCart();
	}

	@DeleteMapping("/{cartId}")
	public Response deleteCart(@RequestParam Integer cartId) {
		return cartService.deleteCart(cartId);
	}

	@GetMapping("get-subtotal-by-userid")
	public Integer getSubTotalByUserId(@RequestParam Integer userId) {
		return cartService.getSubTotalByUserId(userId);
	}

}
