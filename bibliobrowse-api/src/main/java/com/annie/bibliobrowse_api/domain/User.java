package com.annie.bibliobrowse_api.domain;

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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, updatable = false)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

  @Column(nullable = false)
	private String username;

  @Column(nullable = false, length = 16)
  private String salt;

  @Column(name = "password_hash", columnDefinition = "TEXT", nullable = false)
	private String password;

  @Column(name = "profile_image")
	private String profileImage;

}