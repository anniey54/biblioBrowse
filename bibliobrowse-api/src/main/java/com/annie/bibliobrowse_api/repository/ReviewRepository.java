package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
  @Query("SELECT review FROM Review review WHERE review.bookId = :bookId")
  Optional<List<Review>> findAllReviewsFromBook(Long bookId);
}
