package com.annie.bibliobrowse_api.entity;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

  @Column(name = "is_admin", columnDefinition = "boolean default false")
	private Boolean isAdmin;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<UserBookStatus> userBookStatuses;
}