package com.cts.service;

import com.cts.model.Book;
import com.cts.response.Response;

public interface BookService {

	Response saveBook(Book book);

	Response getBook(Integer bookId);

	Response deleteBook(Integer bookId);

	Response getAllBooks();

}
