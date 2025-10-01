package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
	private BookRepository bookRepository;

  @Override
  public List<Book> getAllBooks() {
    return (List<Book>) bookRepository.findAll();
  }

  @Override
  public Book getBook(Long id) {
    return bookRepository.findById(id).get();
  }

  @Override
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book updateBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public String deleteBook(Book book) {
		bookRepository.delete(book);
		return "Book with id " + book.getId() + " is deleted Successfully";
  }

}
