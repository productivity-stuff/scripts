package com.cts.service;

import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dao.OrderDao;
import com.cts.model.OrderDetails;
import com.cts.service.impl.OrderServiceImpl;
import com.cts.utils.DateUtils;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderDao orderDao;

	@InjectMocks
	private OrderServiceImpl serviceImpl;

	int userId = 1;

	@Test
	void testSaveOrder() throws ParseException {
		OrderDetails order = new OrderDetails();
		order.setUserId(1);
		order.setOrderDate(DateUtils.getDateFromStringDate("2023-05-22"));
		order.setOrderStatus("yes");
		order.setShippingAddress("wrangal");
		order.setPaymentMethod("cod");
		order.setBookId(1);
		order.setTotalPrice(234);
		Assertions.assertTrue(orderDao.save(order) != null);
	}

	@Test
	void testGetOrder() {
		Assertions.assertFalse(isOrderDetailsExist(userId));
	}

	@Test
	void testGetAllOrder() {
		List<OrderDetails> orderList = orderDao.findAll();
		Assertions.assertTrue(!orderList.isEmpty() && orderList.size() > 0);
	}

	@Test
	void testDeleteOrder() {
		if (isOrderDetailsExist(userId)) {
			orderDao.deleteById(userId);
			assertFalse(isOrderDetailsExist(userId));
		}
	}

	@Test
	void testGetOrderItemsByUserId() {
		List<OrderDetails> orderList = orderDao.findAllByUserId(userId);
		Assertions.assertTrue(!orderList.isEmpty() && orderList.size() > 0);
	}

	@Test
	public boolean isOrderDetailsExist(int orderId) {
		Optional<OrderDetails> orderDB = orderDao.findById(1);
		boolean isSuccess = orderDB.isPresent();
		return isSuccess;
	}

}
