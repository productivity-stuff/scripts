
package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.UserDetails;
import com.cts.response.Response;
import com.cts.service.UserService;

@RestController

@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("save-user")
	public Response UserDetails(@RequestBody UserDetails userDetails) {

		return userService.saveUserDetails(userDetails);
	}
  
	@GetMapping("get-user-by-userId")
	public Response getUserDetailsParam(@RequestParam Integer userId) {

		return userService.getUserDetails(userId);
	}

	@GetMapping("/login")
	public Response getLogin(@RequestParam String Email,@RequestParam String password) {
		return userService.getLogin(Email,password);
	}
	
	@GetMapping("get-user/{userId}")
	public Response getUserDetails(@PathVariable Integer userId) {

		return userService.getUserDetails(userId);
	}

	@GetMapping("get-all-users")
	public List<UserDetails> getAllUserDetails() {

		return userService.getAllUserDetails();
	}

	@DeleteMapping("delete-user-details")
	public Response deleteUserDetails(@RequestParam Integer userId) {

		 return userService.deleteUserDetails(userId);
	}

}
