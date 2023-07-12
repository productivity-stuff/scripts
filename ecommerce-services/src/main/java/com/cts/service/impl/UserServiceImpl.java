package com.cts.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.constants.CommonConstants;
import com.cts.dao.UserDao;
import com.cts.exception.UserDetailsNotFoundException;
import com.cts.model.UserDetails;
import com.cts.response.Response;
import com.cts.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userDao;
	static List<String> userTypes = Arrays.asList("admin", "user");

	@Override
	public Response getUserDetails(Integer userId) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			LOGGER.debug("Fetching user details for userId: {}", userId);
			Optional<UserDetails> userDetails = userDao.findById(userId);
			if (userDetails.isPresent()) {
				response.setData(userDetails.get());
				response.setStatus(HttpStatus.OK);
				response.setMessage("User details found");
			} else {
				response.setMessage(CommonConstants.UserDetails.USER_NOT_FOUND + userId);
				LOGGER.warn(CommonConstants.UserDetails.USER_NOT_FOUND);
				response.setStatus(HttpStatus.NOT_FOUND);
				throw new UserDetailsNotFoundException("User details not found for ID: " + userId);
			}
		} catch (UserDetailsNotFoundException ex) {
			LOGGER.warn(CommonConstants.UserDetails.USER_NOT_FOUND);
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage(CommonConstants.UserDetails.USER_NOT_FOUND + userId);
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving user details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.UserDetails.GET_TO_USER_FAILED);
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public Response saveUserDetails(UserDetails user) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			if (isUserDetailsRequestInValid(response, user)) {
				return response;
			}
			LOGGER.debug("Fetching user details for userId: {}", user);

			UserDetails savedUser = userDao.save(user);
			response.setData(savedUser);
			response.setStatus(HttpStatus.OK);
			response.setMessage(CommonConstants.UserDetails.SAVED_THE_USERDETAILS_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("Error occurred while saving user details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.UserDetails.SAVED_THE_USERDETAILS_FAILED);
			LOGGER.warn(CommonConstants.UserDetails.SAVED_THE_USERDETAILS_FAILED);
		}
		LOGGER.info("end");
		return response;
	}

	private boolean isUserDetailsRequestInValid(Response response, UserDetails user) {
		return !isUserDetailsRequestValid(response, user);
	}

	private boolean isUserDetailsRequestValid(Response response, UserDetails user) {
		if (user == null) {
			response.setMessage("Oops empty user data");
			return false;
		} else if (user.getUserName() == null) {
			response.setMessage("Oops empty userName ");
			return false;
		} else if (StringUtils.isEmpty(user.getPassword())) {
			response.setMessage("Oops empty password");
			return false;
		} else if (StringUtils.isEmpty(user.getType())) {
			response.setMessage("Oops empty type");
			return false;
		} else if (!userTypes.contains(user.getType())) {
			response.setMessage("Oops, looks like " + user.getType() + " is not accepted");
			return false;
		}
		
		else if (StringUtils.isEmpty(user.getEmail())) {
			response.setMessage("Oops, looks like empty email Id" + user.getEmail() + " is not accepted");
			return false;
		}
		else if (user.getPhoneNo()==0){
			response.setMessage("Oops, looks like empty phoneNo " + user.getPhoneNo() + " is not accepted");
			return false;
		}
		return true;
	}

	

	@Override
	public List<UserDetails> getAllUserDetails() {
		LOGGER.info("START");
		Response response = new Response();
		List<UserDetails> userList = new ArrayList<>();
		try {
			LOGGER.debug("Fetching user details for user details");
			userList = userDao.findAll();

			if (!userList.isEmpty()) {
				response.setData(userList);
				response.setStatus(HttpStatus.OK);
				response.setMessage(CommonConstants.UserDetails.USER_LIST_FOUND);
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(CommonConstants.UserDetails.USER_LIST_NOT_FOUND);
				LOGGER.warn(CommonConstants.UserDetails.USER_LIST_NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving all user details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.UserDetails.USER_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return userList;

	}

	@Override
	public Response deleteUserDetails(Integer userId) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			LOGGER.debug("Deleting user with userId: {}", userId);
			Optional<UserDetails> user = userDao.findById(userId);
			if (user.isPresent()) {
				userDao.deleteById(userId);
				response.setMessage(CommonConstants.UserDetails.USER_DELETED);
			} else {
				response.setMessage(CommonConstants.UserDetails.USER_NOT_FOUND + userId);
				LOGGER.warn(CommonConstants.UserDetails.USER_NOT_FOUND);
				response.setStatus(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while deleting user details.", e);
			response.setMessage(CommonConstants.UserDetails.UNABLE_TO_DELETE_USER);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public Response getLogin(String email, String password) {
		LOGGER.info("Login START");
		Response response = new Response();
		boolean isLoginSuccess = userDao.existsByUserNameAndPassword(email, password);
		response.setData(isLoginSuccess);
		return response;
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
