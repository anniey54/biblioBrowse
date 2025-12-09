package com.annie.bibliobrowse_api.dto;

public class UserBookDTO {
  private Long userId;
  private Long bookId;

  public UserBookDTO(Long userId, Long bookId) {
    this.userId = userId;
    this.bookId = bookId;
  }

  // Getters
  public Long getUserId() {
    return userId;
  }

  public Long getBookId() {
    return bookId;
  }

  // Setters
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }
}
