package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.entity.Review;

public interface ReviewService {
  List<Review>getAllBookReviews(Long bookId);
  Review getReview(Long id);
	Review createReview(Review review);
	String deleteReview(Review review);
}
