package com.annie.bibliobrowse_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookStatusDTO;
import com.annie.bibliobrowse_api.service.UserBookStatusService;


@RestController
@CrossOrigin(maxAge = 3360)
public class UserBookStatusController {
  @Autowired
  private UserBookStatusService userBookStatusService;

  @PostMapping("/api/user-book-status")
  public ResponseEntity<UserBookStatusDTO> createUserBookStatus(@RequestBody UserBookStatusDTO requestDTO) {
    return ResponseEntity.ok(userBookStatusService.createUpdateUserBookStatus(requestDTO.getUserId(), requestDTO.getBookId(), requestDTO.getStatus()));
  }

  @PutMapping("/api/user-book-status")
  public ResponseEntity<UserBookStatusDTO> updateUserBookStatus(@RequestBody UserBookStatusDTO requestDTO) {
    return ResponseEntity.ok(userBookStatusService.createUpdateUserBookStatus(requestDTO.getUserId(), requestDTO.getBookId(), requestDTO.getStatus()));
  }

  @DeleteMapping("/api/user-book-status/user/{userId}/book/{bookId}")
  public ResponseEntity<String> deleteUserBookStatus(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId) {
    UserBookId id = new UserBookId(userId, bookId);
    return ResponseEntity.ok(userBookStatusService.deleteUserBookStatus(id));
  }
  
}
