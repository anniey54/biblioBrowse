package com.annie.bibliobrowse_api.dto;

public class UserBookStatusDTO {
  private Long userId;
  private Long bookId;
  private String status;

  public UserBookStatusDTO(Long userId, Long bookId, String status) {
    this.userId = userId;
    this.bookId = bookId;
    this.status = status;
  }

  // Getters
  public Long getUserId() {
    return userId;
  }

  public Long getBookId() {
    return bookId;
  }

  public String getStatus() {
    return status;
  }

  // Setters
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
