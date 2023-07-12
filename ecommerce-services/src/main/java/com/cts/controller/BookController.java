package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Book;
import com.cts.response.Response;
import com.cts.service.BookService;

@RestController
@ControllerAdvice
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookService bookService;

	@CacheEvict(value = "book", allEntries = true)
	@PostMapping("/save-book")
	public Response saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@GetMapping("/get-book")
	public Response getBookParam(@RequestParam Integer bookId) {
		return bookService.getBook(bookId);
	}

	@Cacheable(value = "book")
	@GetMapping
	public Response getAllBooks() {
		return bookService.getAllBooks();
	}

	@DeleteMapping("/bookId")
	public Response deleteBook(@PathVariable Integer bookId) {
		return bookService.deleteBook(bookId);
	}

}
