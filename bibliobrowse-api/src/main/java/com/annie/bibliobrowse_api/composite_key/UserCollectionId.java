package com.annie.bibliobrowse_api.composite_key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserCollectionId implements Serializable {

  private Long userId;
  private Long collectionId;

  public UserCollectionId() {}

  public UserCollectionId(Long userId, Long collectionId) {
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

  @Override
  public boolean equals(Object statusObject) {
    if (this == statusObject) return true;
    if (!(statusObject instanceof UserCollectionId)) return false;
    UserCollectionId that = (UserCollectionId) statusObject;
    return Objects.equals(userId, that.userId) &&
      Objects.equals(collectionId, that.collectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, collectionId);
  }
}
