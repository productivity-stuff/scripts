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

import com.cts.dao.BookDao;
import com.cts.model.Book;
import com.cts.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookDao bookDao;
   
	@InjectMocks
	private BookServiceImpl serviceImpl;

	int bookId = 1;
	
	@Test
	void testSaveBook() {
		Book book = new Book();
		book.setBookName("godofwar");
		Book bookDetailsDB = bookDao.save(book);
		boolean isSuccess = bookDetailsDB != null && bookDetailsDB.getBookName() != null;
		Assertions.assertTrue(isSuccess);

	}

	@Test
	void testGetBook() {
		Assertions.assertFalse(isBookExist(bookId));
	}

	@Test
	void testDeleteBook() {
		
		if (isBookExist(bookId)) {
			bookDao.deleteById(bookId);
			assertFalse(isBookExist(bookId));
		}

	}

	@Test
	void testGetAllBooks() {
		List<Book> bookList = bookDao.findAll();
		Assertions.assertTrue(!bookList.isEmpty() && bookList.size() > 0);

	}

	public boolean isBookExist(int bookId) {
		Optional<Book> bookDB = bookDao.findById(bookId);
		boolean isSuccess = bookDB.isPresent() && bookDB.get().getBookName() != null;
		return isSuccess;
	}

}
