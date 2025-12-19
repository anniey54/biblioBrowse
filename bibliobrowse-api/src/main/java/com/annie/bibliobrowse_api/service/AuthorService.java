package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.entity.Author;
import com.annie.bibliobrowse_api.entity.Book;

public interface AuthorService {
  List<Author>getAllAuthors();
  List<Book>getBooksByAuthor(Long authorId);
  Author getAuthor(Long id);
	Author createAuthor(Author author);
	Author updateAuthor(Author author);
	String deleteAuthor(Author author);
}
