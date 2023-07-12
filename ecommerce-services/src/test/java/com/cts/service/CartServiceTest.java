package com.cts.service;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dao.CartDao;
import com.cts.model.Cart;
import com.cts.service.impl.CartServiceImpl;
import com.cts.utils.EcommerceUtils;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CartServiceTest {

	@Autowired
	private CartDao cartDao;

	@InjectMocks
	private CartServiceImpl serviceImpl;

	@Autowired
	EcommerceUtils ecommerceUtils;

	Integer userId = 2;
	int cartId = 1;

	@Test
	void testSaveCart() {
		Cart cart = new Cart();
		cart.setBookId(2);
		cart.setQuantity(2);
		cart.setUserId(userId);
		Cart cartDB = ecommerceUtils.saveCartDetails(cart);
		boolean isSuccess = cartDB != null;
		Assertions.assertTrue(isSuccess);
	}

	@Test
	void testGetCart() {
		Assertions.assertTrue(isCartExist(cartId));
	}

	@Test
	void testGetAllCart() {
		List<Cart> cartList = cartDao.findAll();
		Assertions.assertTrue(!cartList.isEmpty() && cartList.size() > 0);
	}

	@Test
	void testDeleteCart() {

		if (isCartExist(cartId)) {
			cartDao.deleteById(cartId);
			assertFalse(isCartExist(cartId));
		}
	}

	@Test
	void testGetCartItemsByUserId() {
		List<Cart> cartList = cartDao.findAllByUserId(userId);
	}

	@Test
	void testGetSubTotalByUserId() {
		int subTotal = 0;
		List<Cart> cartList = cartDao.findAllByUserId(userId);
		for (int i = 0; i < cartList.size(); i++) {
			subTotal += cartList.get(i).getTotalPrice();
		}
		System.out.println("Total " + subTotal);
	}

	
    @Test
	public boolean isCartExist(int cartId) {
		Optional<Cart> cartDB = cartDao.findById(cartId);
		boolean isSuccess = cartDB.isPresent();
		return isSuccess;
	}

}
