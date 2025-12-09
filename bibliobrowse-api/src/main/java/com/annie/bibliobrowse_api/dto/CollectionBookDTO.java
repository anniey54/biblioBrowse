package com.annie.bibliobrowse_api.dto;

public class CollectionBookDTO {
  private Long collectionId;
  private Long bookId;

  public CollectionBookDTO(Long collectionId, Long bookId) {
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
}
