package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.domain.Author;

public interface AuthorService {
  List<Author>getAllAuthors();
  Author getAuthor(Long id);
	Author createAuthor(Author author);
	Author updateAuthor(Author author);
	String deleteAuthor(Author author);
}
