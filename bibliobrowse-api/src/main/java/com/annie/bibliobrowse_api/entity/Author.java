package com.annie.bibliobrowse_api.entity;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "authors")
public class Author {
  @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "author_id", unique = true, updatable = false)
	private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;
  
  @Column(name = "profile_image")
  private String profileImage;

  @Column(columnDefinition = "TEXT default ''")
  private String about;

  @Column(columnDefinition = "char(18) default ''")
  private String dateOfBirth;

  @Column(columnDefinition = "varchar(255) default ''")
  private String website;

  @Column(name = "social_x", columnDefinition = "varchar(255) default ''")
  private String socialX;

  @Column(columnDefinition = "varchar(255) default ''")
  private String instagram;

}
