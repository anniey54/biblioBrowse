package com.annie.bibliobrowse_api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewTests {

  @Autowired
  MockMvc mvc;

  @Test
	void getReviewById() throws Exception {
    long reviewId = 1;

    mvc.perform(MockMvcRequestBuilders.get("/api/reviews/{id}", reviewId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(reviewId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").value(2))
      .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(5))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Wow, this book is the best dystopian fiction I have read so far!"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("The Hunger Games is an absolutely captivating and thought-provoking read! Suzanne Collins masterfully crafts a dystopian world that pulls you in from the very first page. Katniss Everdeen is an unforgettable protagonist -- her strength, resourcefulness, and unwavering love for her family make her incredibly compelling. The tension is palpable throughout, keeping you on the edge of your seat, and the underlying themes of survival, sacrifice, and the corrupting influence of power are explored with incredible depth. A truly brilliant and unputdownable start to an iconic series!"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfLikes").value(1280))
      .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value("2025-09-26"));
  }

  @Test
	void addReview() throws Exception {
    String reviewJson = "{\"userId\":13,\"bookId\":7,\"rating\":1,\"title\":\"Test review 1\",\"comment\":\"This is comment for test review 1\",\"numberOfLikes\":20}";

    mvc.perform(MockMvcRequestBuilders.post("/api/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content(reviewJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/reviews/book/{id}", 7))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.title == 'Test review 1' && @.userId == 13 && @.bookId == 7 && @.rating == 1)]", Matchers.hasSize(1)));
  }

  // @Test
	// void updateReview() throws Exception {
  //   String reviewJson = "{\"id\":8,\"userId\":13,\"bookId\":7,\"rating\":3,\"title\":\"Updated Test review 1\",\"comment\":\"This is comment for updated test review 1\",\"numberOfLikes\":20}";

  //   mvc.perform(MockMvcRequestBuilders.put("/api/reviews")
  //         .contentType(MediaType.APPLICATION_JSON)
  //         .content(reviewJson))
  //     .andExpect(MockMvcResultMatchers.status().isOk())
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(8))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(13))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").value(7))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(3))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Test review 1"))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("This is comment for updated test review 1"))
  //     .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfLikes").value(20));
  // }

  @Test
	void deleteReview() throws Exception {
    long reviewId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/reviews/{id}", reviewId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
