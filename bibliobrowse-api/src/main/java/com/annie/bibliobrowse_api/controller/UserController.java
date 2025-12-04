package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.dto.UserBookStatusDTO;
import com.annie.bibliobrowse_api.entity.User;
import com.annie.bibliobrowse_api.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(maxAge = 3360)
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/api/users")
  public ResponseEntity<List<User>> getAllUsers() {
      return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/api/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
      return ResponseEntity.ok(userService.getUser(id));
  }

  @GetMapping("/api/users/{id}/book-status")
  public ResponseEntity<List<UserBookStatusDTO>> getAllBooksWithStatus(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.getBooksWithStatus(id));
  }
  
  
  @PostMapping("/api/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
      return ResponseEntity.ok(userService.createUser(user));
  }

  @PutMapping("/api/users")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.updateUser(user));
  }

  @DeleteMapping("/api/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    User userObject = userService.getUser(id);
    String resultMessage = null;

    if (userObject != null) {
      resultMessage = userService.deleteUser(userObject);
    }
    return ResponseEntity.ok(resultMessage);
  }
}
