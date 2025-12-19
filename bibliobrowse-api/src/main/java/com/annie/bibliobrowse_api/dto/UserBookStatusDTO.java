package com.annie.bibliobrowse_api.dto;

import com.annie.bibliobrowse_api.type.BookStatus;

public class UserBookStatusDTO {
  private Long userId;
  private Long bookId;
  private BookStatus status;

  public UserBookStatusDTO(Long userId, Long bookId, BookStatus status) {
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

  public BookStatus getStatus() {
    return status;
  }

  // Setters
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public void setStatus(BookStatus status) {
    this.status = status;
  }
}
