package com.cts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.OrderDetails;

public interface OrderDao extends JpaRepository<OrderDetails, Integer>{

	List<OrderDetails> findAllByUserId(Integer userId);

}
