package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.entity.Book;

public interface BookService {
  List<Book>getAllBooks();
	Book getBook(Long id);
	Book createBook(Book book);
	Book updateBook(Book book);
	String deleteBook(Book book);
}
