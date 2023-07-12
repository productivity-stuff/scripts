package com.cts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.constants.CommonConstants;
import com.cts.dao.BookDao;
import com.cts.dao.CartDao;
import com.cts.model.Cart;
import com.cts.response.Response;
import com.cts.service.CartService;
import com.cts.utils.EcommerceUtils;

@Service
public class CartServiceImpl implements CartService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartDao cartDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	EcommerceUtils ecommerceUtils;

	@Override
	public Response saveCart(Cart cart) {
		LOGGER.info("start");
		Response response = new Response();
		try {
			if (isValidCartRequest(response, cart)) {
				Cart savedCart = ecommerceUtils.saveCartDetails(cart);
				if (savedCart !=null) {
					response.setData(savedCart);
					response.setStatus(HttpStatus.OK);
					response.setMessage(CommonConstants.Cart.ADD_TO_CART_SUCCESS);
				}else {
					response.setMessage("Book Not found");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while saving cart details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Cart.ADD_TO_CART_FAILED);
			LOGGER.warn(CommonConstants.Cart.ADD_TO_CART_FAILED);
		}
		LOGGER.info("end");
		return response;
	}

	private boolean isValidCartRequest(Response response, Cart cart) {
		if (cart == null) {
			response.setMessage("Empty cart object");
			return false;
		} else if (cart.getBookId() == null) {
			response.setMessage("Empty cart book id");
			return false;
		} else if (cart.getQuantity() == null) {
			response.setMessage("Empty cart quantity");
			return false;
		} else if (cart.getUserId() == null) {
			response.setMessage("Empty cart user id");
			return false;
		}
		return true;
	}

	@Override
	public Response getCart(Integer cartId) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			LOGGER.debug("Fetching cart details for cartId: {}", cartId);
			Optional<Cart> cart = cartDao.findById(cartId);
			if (cart.isPresent()) {
				response.setData(cart.get());
				response.setStatus(HttpStatus.OK);
				response.setMessage("Cart details found");
			} else {
				response.setMessage(CommonConstants.Cart.GET_TO_CART_SUCCESS + cartId);
				LOGGER.warn(CommonConstants.Cart.CART_NOT_FOUND);
				response.setStatus(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving cart details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Cart.GET_TO_CART_FAILED);
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public List<Cart> getAllCart() {
		LOGGER.info("start");
		Response response = new Response();
		List<Cart> cartList = new ArrayList<>();
		try {
			LOGGER.debug("Fetching all cart details");
			cartList = cartDao.findAll();
			if (!cartList.isEmpty()) {
				response.setData(cartList);
				response.setStatus(HttpStatus.OK);
				response.setMessage(CommonConstants.Cart.CART_LIST_FOUND);
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(CommonConstants.Cart.CART_LIST_NOT_FOUND);
				LOGGER.warn(CommonConstants.Cart.CART_LIST_NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving all cart details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Cart.CART_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return cartList;
	}

	@Override
	public Response deleteCart(Integer cartId) {
		LOGGER.info("start");
		Response response = new Response();
		try {
			LOGGER.debug("Deleting cart with cartId: {}", cartId);
			Optional<Cart> cart = cartDao.findById(cartId);
			if (cart.isPresent()) {
				cartDao.deleteById(cartId);
				response.setMessage(CommonConstants.Cart.CART_DELETED);
			} else {
				LOGGER.warn(CommonConstants.Cart.CART_NOT_FOUND);
				response.setMessage(CommonConstants.Cart.CART_NOT_FOUND + cartId);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while deleting cart details.", e);
			response.setMessage(CommonConstants.Cart.UNABLE_TO_DELETE_CART);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;

	}

	@Override
	public List<Cart> getCartItemsByUserId(Integer userId) {
		List<Cart> cartList = cartDao.findAllByUserId(userId);
		return cartList;
	}

	@Override
	public Integer getSubTotalByUserId(Integer userId) {
		int subTotal = 0;
		List<Cart> cartList = getCartItemsByUserId(userId);
		for (int i = 0; i < cartList.size(); i++) {
			subTotal += cartList.get(i).getTotalPrice();
		}
		System.out.println("Total " + subTotal);
		return subTotal;
	}

}
