package com.annie.bibliobrowse_api.entity;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "reviews")
public class Review {
  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "review_id", unique = true, updatable = false)
	private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "book_id", nullable = false)
  private Long bookId;

  @Column(columnDefinition = "Integer default 1")
  private Integer rating;

  @Column(columnDefinition = "varchar(255) default ''")
  private String title;
  
  @Column(columnDefinition = "TEXT default ''")
  private String comment;
  
  @Column(name = "number_of_likes", columnDefinition = "Integer default 0")
  private Integer numberOfLikes;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "created_at", columnDefinition = "default CURRENT_DATE")
  private Date createdAt;
}