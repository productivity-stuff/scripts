package com.cts.service;

import java.util.List;

import com.cts.model.OrderDetails;
import com.cts.response.Response;

public interface OrderService {
	Response getOrder(Integer orderId);

	Response saveOrder(OrderDetails order);

	List<OrderDetails> getAllOrder();

	Response deleteOrder(Integer orderId);

	List<OrderDetails> getOrderItemsByUserId(Integer orderId);

	

}
