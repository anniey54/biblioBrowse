package com.annie.bibliobrowse_api.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.annie.bibliobrowse_api.type.CollectionStatus;
import com.annie.bibliobrowse_api.type.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "collections")
public class Collection {
  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "collection_id", unique = true, updatable = false)
	private Long id;

  // id from user table
  @Column(nullable = false)
  private Long creator;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "VARCHAR(10) default 'Private'")
  private CollectionStatus status;
  
  @Column(nullable = false, columnDefinition = "varchar(255)")
  private String title;

  @Column(columnDefinition = "TEXT default ''")
  private String summary;

  @Column(name = "number_of_likes", columnDefinition = "Integer default 0")
  private Integer numberOfLikes;

  @Enumerated(EnumType.STRING)
  @Column(name = "genres", columnDefinition = "VARCHAR(25) ARRAY default '{}'") 
  private List<Genre> genres;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "created_at", columnDefinition = "default CURRENT_DATE")
  private Date createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "last_updated_at", columnDefinition = "default CURRENT_DATE")
  private Date lastUpdatedAt;
}