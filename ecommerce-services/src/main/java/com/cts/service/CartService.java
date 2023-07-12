package com.cts.service;

import java.util.List;

import com.cts.model.Cart;
import com.cts.response.Response;

public interface CartService {

	Response saveCart(Cart cart);

	Response getCart(Integer cartId);

	List<Cart> getAllCart();

	Response deleteCart(Integer cartId);

	List<Cart> getCartItemsByUserId(Integer userId);

	Integer getSubTotalByUserId(Integer userId);

}
