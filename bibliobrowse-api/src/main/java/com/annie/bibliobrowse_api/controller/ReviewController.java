package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.entity.Review;
import com.annie.bibliobrowse_api.service.ReviewService;

@RestController
@CrossOrigin(maxAge = 3360)
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping("/api/reviews/{id}")
  public ResponseEntity<Review> getReview(@PathVariable("id") Long id) {
      return ResponseEntity.ok(reviewService.getReview(id));
  }

  @GetMapping("/api/reviews/book/{id}")
  public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable("id") Long id) {
      return ResponseEntity.ok(reviewService.getAllBookReviews(id));
  }
  
  @PostMapping("/api/reviews")
  public ResponseEntity<Review> createReview(@RequestBody Review review) {
      return ResponseEntity.ok(reviewService.createReview(review));
  }

  @DeleteMapping("/api/reviews/{id}")
  public ResponseEntity<String> deleteReview(@PathVariable("id") Long id) {
    Review reviewObject = reviewService.getReview(id);
    String resultMessage = null;

    if (reviewObject != null) {
      resultMessage = reviewService.deleteReview(reviewObject);
    }
    return ResponseEntity.ok(resultMessage);
  }
}
