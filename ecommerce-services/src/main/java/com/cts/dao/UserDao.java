package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.UserDetails;

public interface UserDao extends JpaRepository<UserDetails, Integer>{

	boolean existsByUserNameAndPassword(String email, String password);

}
