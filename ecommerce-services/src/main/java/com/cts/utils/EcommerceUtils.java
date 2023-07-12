package com.cts.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dao.BookDao;
import com.cts.dao.CartDao;
import com.cts.model.Book;
import com.cts.model.Cart;
@Service
public class EcommerceUtils {

	@Autowired
	BookDao bookDao;

	@Autowired
	CartDao cartDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(EcommerceUtils.class);

	public Cart saveCartDetails(Cart cart) {
		
		Optional<Book> book = bookDao.findById(cart.getBookId());
		if (book.isPresent() && book.get().getBookPrice() != 0) {
			double totalPrice = book.get().getBookPrice() * cart.getQuantity();
			cart.setTotalPrice(totalPrice);
			LOGGER.info("Saving cart details: {}", cart);
			Cart savedCartDetails = cartDao.save(cart);
			return savedCartDetails;
		}else {
			LOGGER.info("Book not found: {}", cart.getBookId());
			return null;
		}
	}
}
