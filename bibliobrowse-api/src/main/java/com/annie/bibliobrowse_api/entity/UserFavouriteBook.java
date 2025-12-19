package com.annie.bibliobrowse_api.entity;

import org.hibernate.annotations.DynamicInsert;

import com.annie.bibliobrowse_api.composite_key.UserBookId;

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
@Table(name = "user_favourite_books")
public class UserFavouriteBook {
  @EmbeddedId
  private UserBookId id;
  
  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @MapsId("bookId")
  @JoinColumn(name = "book_id")
  private Book book;
}
