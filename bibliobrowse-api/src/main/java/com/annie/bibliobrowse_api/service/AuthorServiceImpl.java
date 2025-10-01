package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.entity.Author;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  @Override
	public List<Author>getAllAuthors() {
		return (List<Author>) authorRepository.findAll();
	}

  @Override
  public Author getAuthor(Long id) {
    return authorRepository.findById(id).get();
  }

  @Override
  public Author createAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Author updateAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public String deleteAuthor(Author author) {
    authorRepository.delete(author);
    return "Author with id " + author.getId() + " is deleted Successfully";
  }

}
