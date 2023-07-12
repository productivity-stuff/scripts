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

import com.cts.model.OrderDetails;
import com.cts.response.Response;
import com.cts.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("save-order")
	public Response saveOrder(@RequestBody OrderDetails order) {
		return orderService.saveOrder(order);
	}

	@GetMapping("get-by-orderId")
	public Response getOderParam(@RequestParam Integer orderId) {

		return orderService.getOrder(orderId);
	}

	@GetMapping("get-all-order")
	public List<OrderDetails> getAllOrder() {

		return orderService.getAllOrder();
	}

	@DeleteMapping("delete-order-by-orderId")
	public Response deleteOrder(@RequestParam Integer orderId) {

		return orderService.deleteOrder(orderId);
	}
	
	@GetMapping("get-orderitems-by-userid")
	public List<OrderDetails> getOrderItemsByUserIdParam(@RequestParam Integer userId){
		return orderService.getOrderItemsByUserId(userId);
		
	}
}
