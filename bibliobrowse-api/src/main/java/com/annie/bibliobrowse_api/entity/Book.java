package com.annie.bibliobrowse_api.entity;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.annie.bibliobrowse_api.type.AgeRange;
import com.annie.bibliobrowse_api.type.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "book_id", unique = true, updatable = false)
  private Long id;

  @Column(length = 255, nullable = false)
  private String title;

  @Column(name = "image_cover")
  private String imageCover;

  @Column(name = "author_id", nullable = false)
  private Long author;

  @Enumerated(EnumType.STRING)
  @Column(name = "genres", columnDefinition = "VARCHAR(25) ARRAY default '{}'") 
  private List<Genre> genres;

  @Column(columnDefinition = "TEXT default ''")
  private String summary;

  @Enumerated(EnumType.STRING)
  @Column(name = "audience", columnDefinition = "VARCHAR(20) default 'Young_adult'")
  private AgeRange audience;

  @Column(columnDefinition = "default 'English'", length = 20, nullable = false)
  private String language;

  @Column(name = "number_page", columnDefinition = "INTEGER default 0")
  private Integer numberPage;

  @Column(columnDefinition = "default ''", length = 225)
  private String publisher;

  @Column(columnDefinition = "default ''", length = 225)
  private String isbn;

  @Column(columnDefinition = "default ''", length = 225)
  private String series;

  @Column(columnDefinition = "INTEGER default NULL")
  private Integer volume;

  @JsonIgnore
  @OneToMany(mappedBy = "book")
  private List<UserBookStatus> userBookStatuses;
}
