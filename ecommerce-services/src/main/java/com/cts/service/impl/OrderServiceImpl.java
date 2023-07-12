package com.cts.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.constants.CommonConstants;
import com.cts.dao.OrderDao;
import com.cts.model.OrderDetails;
import com.cts.model.UserDetails;
import com.cts.response.Response;
import com.cts.service.OrderService;
import com.cts.utils.DateUtils;

@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderDao orderDao;
	
	static int ZERO = BigDecimal.ZERO.intValue();

	@Override
	public Response getOrder(Integer orderId) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			LOGGER.debug("Fetching order details for orderId: {}", orderId);
			Optional<OrderDetails> order = orderDao.findById(orderId);
			if (order.isPresent()) {
				response.setData(order.get());
				response.setStatus(HttpStatus.OK);
				response.setMessage("Order details found");
			} else {
				response.setMessage(CommonConstants.OrderDetails.GET_TO_ORDER_SUCCESS + orderId);
				LOGGER.warn(CommonConstants.OrderDetails.ORDER_NOT_FOUND);
				response.setStatus(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving order details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.OrderDetails.GET_TO_ORDER_FAILED);
		}
		LOGGER.info("end");
		return response;

	}

	@Override
	public Response saveOrder(OrderDetails order) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			if(isOrderDetailsRequestInValid(response, order)) {
				return response;
		 }
			LOGGER.debug("Saving order details: {}", order);
			order.setOrderDate(DateUtils.getCurrentDate());
			OrderDetails savedOrder = orderDao.save(order);
			response.setData(savedOrder);
			response.setStatus(HttpStatus.OK);
			response.setMessage(CommonConstants.OrderDetails.SAVED_THE_ORDERDETAILS_SUCCESS );
		} catch (Exception e) {
			LOGGER.error("Error occurred while saving order details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.OrderDetails.SAVED_THE_ORDERDETAILS_FAILED);
			LOGGER.warn(CommonConstants.OrderDetails.SAVED_THE_ORDERDETAILS_FAILED);
		}
		LOGGER.info("end");
		return response;
	}
	
	private boolean isOrderDetailsRequestInValid(Response response, OrderDetails order) {
		return !isOrderDetailsRequestValid(response, order);
	}

	private boolean isOrderDetailsRequestValid(Response response, OrderDetails order) {
		if (order == null) {
			response.setMessage("Oops empty order data");
			return false;
		} else if (order.getUserId() == ZERO) {
			response.setMessage("Oops empty userID ");
			return false;
		} else if (order.getBookId() < ZERO) {
			response.setMessage("Oops empty bookId");
			return false;
		} else if (order.getOrderStatus() == null) {
			response.setMessage("Oops empty status");
			return false;
		}
		else if (order.getShippingAddress() == null) {
			response.setMessage("Oops empty shipping address");
			return false;
		}
		else if (order.getPaymentMethod() == null) {
			response.setMessage("Oops empty payment method");
			return false;
		}
		else if (order.getOrderDate() == null) {
			response.setMessage("Oops empty order date");
			return false;
		}
		else if (order.getTotalPrice() == ZERO) {
			response.setMessage("Oops empty price");
			return false;
		}
		return true;
	}


	@Override
	public List<OrderDetails> getAllOrder() {
		LOGGER.info("START");
		Response response = new Response();
		List<OrderDetails> orderList = new ArrayList<>();
		try {
			LOGGER.debug("Saving order details: {}");
			orderList = orderDao.findAll();

			if (!orderList.isEmpty()) {
				response.setData(orderList);
				response.setStatus(HttpStatus.OK);
				response.setMessage(CommonConstants.OrderDetails.ORDER_LIST_FOUND);
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(CommonConstants.OrderDetails.ORDER_LIST_NOT_FOUND);
				LOGGER.warn(CommonConstants.OrderDetails.ORDER_LIST_NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving all order details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.OrderDetails.ORDER_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return orderList;

	}

	@Override
	public Response deleteOrder(Integer orderId) {
		LOGGER.info("start");
		Response response = new Response();
		try {
			LOGGER.debug("Deleting order with orderId: {}", orderId);
			Optional<OrderDetails> order = orderDao.findById(orderId);
			if (order.isPresent()) {
				orderDao.deleteById(orderId);
				response.setMessage(CommonConstants.OrderDetails.ORDER_DELETED);
			} else {
				response.setMessage(CommonConstants.OrderDetails.ORDER_NOT_FOUND + orderId);
				LOGGER.warn(CommonConstants.OrderDetails.ORDER_NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while deleting order details.", e);
			response.setMessage(CommonConstants.OrderDetails.UNABLE_TO_DELETE_ORDER);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);  
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public List<OrderDetails> getOrderItemsByUserId(Integer userId) {
		List<OrderDetails> orderDetails = orderDao.findAllByUserId(userId);
		return orderDetails;
	}

}
