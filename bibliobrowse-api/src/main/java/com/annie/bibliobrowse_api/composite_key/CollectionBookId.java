package com.annie.bibliobrowse_api.composite_key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CollectionBookId implements Serializable {

  private Long collectionId;
  private Long bookId;

  public CollectionBookId() {}

  public CollectionBookId(Long collectionId, Long bookId) {
    this.collectionId = collectionId;
    this.bookId = bookId;
  }

  // Getters
  public Long getCollectionId() {
    return collectionId;
  }

  public Long getBookId() {
    return bookId;
  }

  // Setters
  public void setCollectionId(Long collectionId) {
    this.collectionId = collectionId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  @Override
  public boolean equals(Object statusObject) {
    if (this == statusObject) return true;
    if (!(statusObject instanceof CollectionBookId)) return false;
    CollectionBookId that = (CollectionBookId) statusObject;
    return Objects.equals(collectionId, that.collectionId) &&
      Objects.equals(bookId, that.bookId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectionId, bookId);
  }


}
