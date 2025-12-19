package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.service.BookService;

@RestController
@CrossOrigin(maxAge = 3360)
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping("/api/books")
  public ResponseEntity<List<Book>> getAllBooks() {
      return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/api/books/{id}")
  public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
      return ResponseEntity.ok(bookService.getBook(id));
  }
  
  @PostMapping("/api/books")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
      return ResponseEntity.ok(bookService.createBook(book));
  }

  @PutMapping("/api/books")
  public ResponseEntity<Book> updateBook(@RequestBody Book book) {
    return ResponseEntity.ok(bookService.updateBook(book));
  }

  @DeleteMapping("/api/books/{id}")
  public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
    Book bookObject = bookService.getBook(id);
    String resultMessage = null;

    if (bookObject != null) {
      resultMessage = bookService.deleteBook(bookObject);
    }
    return ResponseEntity.ok(resultMessage);
  }

}
