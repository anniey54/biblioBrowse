package com.annie.bibliobrowse_api.dto;

public class UserCollectionDTO {
  private Long userId;
  private Long collectionId;

  public UserCollectionDTO(Long userId, Long collectionId) {
    this.userId = userId;
    this.collectionId = collectionId;
  }

  // Getters
  public Long getUserId() {
    return userId;
  }

  public Long getCollectionId() {
    return collectionId;
  }

  // Setters
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setCollectionId(Long collectionId) {
    this.collectionId = collectionId;
  }
}
