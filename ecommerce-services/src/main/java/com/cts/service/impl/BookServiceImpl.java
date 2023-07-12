package com.cts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.constants.CommonConstants;
import com.cts.dao.BookDao;
import com.cts.model.Book;
import com.cts.response.Response;
import com.cts.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookDao bookDao;

	@Override
	public Response saveBook(Book book) {
		LOGGER.info("START");
		Response response = new Response();
		try {
			if (isBookRequestInValid(response, book)) {
				return response;
			}
			LOGGER.debug("Saving book details: {}", book);
			Optional<Book> existingBook = bookDao.findByBookName(book.getBookName());

			if (existingBook.isPresent()) {
				response.setMessage("Book already exists.");
			} else {
				Book savedBookDetails1 = bookDao.save(book);
				response.setData(savedBookDetails1);
				response.setMessage(CommonConstants.Book.SAVED_THE_BOOK_SUCCESS);
			}
			response.setStatus(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occurred while saving book details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Book.SAVED_THE_BOOK_FAILED);
			LOGGER.warn(CommonConstants.Book.SAVED_THE_BOOK_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;
	}

	private boolean isBookRequestInValid(Response response, Book book) {
		return !isBookRequestValid(response, book);
	}

	private boolean isBookRequestValid(Response response, Book book) {
		if (book == null) {
			response.setMessage("Oops empty book data");
			return false;
		} else if (book.getBookName() == null || book.getBookName().isEmpty()) {
			response.setMessage("Oops empty book name");
			return false;
		} else if (book.getBookPrice() < 1) {
			response.setMessage("Oops price should be greater than 1");
			return false;
		}
		return true;
	}

	@Override
	public Response getBook(Integer bookId) {
		LOGGER.info("Start");
		Response response = new Response();
		try {
			LOGGER.debug("Fetching book details for bookId: {}", bookId);
			Optional<Book> book = bookDao.findById(bookId);
			if (book.isPresent()) {
				response.setData(book.get());
				response.setMessage(CommonConstants.Book.BOOK_FOUND);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Book details found");
			} else {
				response.setMessage(CommonConstants.Book.BOOK_NOT_FOUND + bookId);
				LOGGER.warn(CommonConstants.Book.BOOK_NOT_FOUND);
				response.setStatus(HttpStatus.NOT_FOUND);
			}
			response.setStatus(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving book details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Book.BOOK_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public Response getAllBooks() {
		LOGGER.info("start");
		Response response = new Response();
		List<Book> booksList = new ArrayList<>();
		try {
			LOGGER.debug("Fetching all book details");
			booksList = bookDao.findAll();

			if (!booksList.isEmpty()) {
				response.setData(booksList);
				response.setStatus(HttpStatus.OK);
				response.setMessage(CommonConstants.Book.BOOK_LIST_FOUND);
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(CommonConstants.Book.BOOK_LIST_NOT_FOUND);
				LOGGER.warn(CommonConstants.Book.BOOK_LIST_NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving all book details.", e);
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(CommonConstants.Book.BOOK_FAILED);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;
	}

	@Override
	public Response deleteBook(Integer bookId) {
		LOGGER.info("start");
		Response response = new Response();
		try {
			LOGGER.debug("Deleting book with bookId: {}", bookId);
			Optional<Book> book = bookDao.findById(bookId);
			if (book.isPresent()) {
				bookDao.deleteById(bookId);
				response.setMessage(CommonConstants.Book.BOOK_DELETED);
			} else {
				LOGGER.warn(CommonConstants.Book.BOOK_NOT_FOUND);
				response.setMessage(CommonConstants.Book.BOOK_NOT_FOUND + bookId);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred while deleting book details.", e);
			response.setMessage(CommonConstants.Book.UNABLE_TO_DELETE_BOOK);
			e.getLocalizedMessage();
		}
		LOGGER.info("end");
		return response;

	}
}
