package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.entity.Review;
import com.annie.bibliobrowse_api.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
	private ReviewRepository reviewRepository;

  @Override
  public List<Review> getAllBookReviews(Long bookId) {
    List<Review> reviews = reviewRepository.findAllReviewsFromBook(bookId)
      .orElseThrow(() -> new RuntimeException("Reviews not found from book id " + bookId));
    return reviews;
  }

  @Override
  public Review getReview(Long id) {
    return reviewRepository.findById(id).get();
  }

  @Override
  public Review createReview(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public String deleteReview(Review review) {
    reviewRepository.delete(review);
    return "Review with id " + review.getId() + " is deleted Successfully";
  }

}
