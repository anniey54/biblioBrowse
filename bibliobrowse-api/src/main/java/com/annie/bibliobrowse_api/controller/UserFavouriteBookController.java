package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookDTO;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.service.UserFavouriteBookService;

@RestController
@CrossOrigin(maxAge = 3360)
public class UserFavouriteBookController {
  @Autowired
  private UserFavouriteBookService userFavouriteBookService;
  
  @GetMapping("/api/favourite-book/user/{userId}")
  public ResponseEntity<List<Book>> getAllFavouriteBooks(@PathVariable("userId") Long userId) {
      return ResponseEntity.ok(userFavouriteBookService.getBooksByUserId(userId));
  }
  
  @PostMapping("/api/favourite-book")
  public ResponseEntity<UserBookDTO> createFavouriteBook(@RequestBody UserBookDTO requestDTO) {
    return ResponseEntity.ok(userFavouriteBookService.createFavouriteBook(requestDTO.getUserId(), requestDTO.getBookId()));
  }

  @DeleteMapping("/api/favourite-book/user/{userId}/book/{bookId}")
  public ResponseEntity<String> deleteFavouriteBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId) {
    UserBookId id = new UserBookId(userId, bookId);
    return ResponseEntity.ok(userFavouriteBookService.deleteFavouriteBook(id));
  }
}
