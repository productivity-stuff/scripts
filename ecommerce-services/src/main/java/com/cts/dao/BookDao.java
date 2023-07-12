package com.cts.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

	Optional<Book> findByBookName(String bookName);

}
