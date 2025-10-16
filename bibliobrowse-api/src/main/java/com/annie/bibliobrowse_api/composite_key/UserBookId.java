package com.annie.bibliobrowse_api.composite_key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserBookId implements Serializable {

  private Long userId;
  private Long bookId;

  public UserBookId() {}

  public UserBookId(Long userId, Long bookId) {
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

  @Override
  public boolean equals(Object statusObject) {
    if (this == statusObject) return true;
    if (!(statusObject instanceof UserBookId)) return false;
    UserBookId that = (UserBookId) statusObject;
    return Objects.equals(userId, that.userId) &&
      Objects.equals(bookId, that.bookId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, bookId);
  }


}
