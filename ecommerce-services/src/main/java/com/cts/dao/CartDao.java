package com.cts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

	List<Cart> findAllByUserId(Integer userId);

}
