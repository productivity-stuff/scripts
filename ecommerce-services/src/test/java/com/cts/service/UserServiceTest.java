package com.cts.service;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dao.UserDao;
import com.cts.model.UserDetails;
import com.cts.service.impl.UserServiceImpl;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserDao userDao;

	@InjectMocks
	private UserServiceImpl serviceImpl;

	@Test
	void testSaveUserDetails() {

		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("rakesh");
		UserDetails userDetailsDB = userDao.save(userDetails);
		boolean isSuccess = userDetailsDB != null && userDetailsDB.getUserName() != null;
		Assertions.assertTrue(isSuccess);

	}

	@Test
	void testGetUserDetails() {
		Assertions.assertFalse(isUserDetailsExist(1));
	}

	@Test
	void testGetAllUserDetails() {

		List<UserDetails> userDetailsList = userDao.findAll();
		Assertions.assertTrue(!userDetailsList.isEmpty() && userDetailsList.size() > 0);

	}

	@Test
	void testDeleteUserDetails() {
		int userId = 1;
		if (isUserDetailsExist(userId)) {
			userDao.deleteById(userId);
			assertFalse(isUserDetailsExist(userId));
		}
	}

	public boolean isUserDetailsExist(int userId) {
		Optional<UserDetails> userDetailsDB = userDao.findById(1);
		boolean isSuccess = userDetailsDB.isPresent() && userDetailsDB.get().getUserName() != null;
		return isSuccess;
	}

}
