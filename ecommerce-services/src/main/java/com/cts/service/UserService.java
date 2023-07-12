package com.cts.service;

import java.util.List;

import com.cts.model.UserDetails;
import com.cts.response.Response;

public interface UserService {

	Response getUserDetails(Integer userId);

	Response saveUserDetails(UserDetails userDetails);
	
	boolean existsByEmail(String email);

	List<UserDetails> getAllUserDetails();

	Response deleteUserDetails(Integer userId);
	

	Response getLogin(String email, String password);

}
