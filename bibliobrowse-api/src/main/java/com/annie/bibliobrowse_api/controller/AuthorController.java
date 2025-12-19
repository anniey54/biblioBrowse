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

import com.annie.bibliobrowse_api.entity.Author;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.service.AuthorService;

@RestController
@CrossOrigin(maxAge = 3360)
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping("/api/authors")
  public ResponseEntity<List<Author>> getAllAuthors() {
      return ResponseEntity.ok(authorService.getAllAuthors());
  }

  @GetMapping("/api/authors/{id}")
  public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
      return ResponseEntity.ok(authorService.getAuthor(id));
  }

  @GetMapping("/api/authors/{id}/books")
  public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("id") Long id) {
      return ResponseEntity.ok(authorService.getBooksByAuthor(id));
  }

  @PostMapping("/api/authors")
  public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
      return ResponseEntity.ok(authorService.createAuthor(author));
  }

  @PutMapping("/api/authors")
  public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
    return ResponseEntity.ok(authorService.updateAuthor(author));
  }

  @DeleteMapping("/api/authors/{id}")
  public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
    Author authorObject = authorService.getAuthor(id);
    String resultMessage = null;

    if (authorObject != null) {
      resultMessage = authorService.deleteAuthor(authorObject);
    }
    return ResponseEntity.ok(resultMessage);
  }

}
