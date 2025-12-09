package com.annie.bibliobrowse_api.entity;

import org.hibernate.annotations.DynamicInsert;

import com.annie.bibliobrowse_api.composite_key.UserCollectionId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_favourite_collections")
public class UserFavouriteCollection {
  @EmbeddedId
  private UserCollectionId id;
  
  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @MapsId("collectionId")
  @JoinColumn(name = "collection_id")
  private Collection collection;
}
