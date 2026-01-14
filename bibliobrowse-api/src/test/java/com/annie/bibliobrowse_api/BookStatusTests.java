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
class BookStatusTests {

  @Autowired
  MockMvc mvc;

  @Test
	void addBookStatus() throws Exception {
    String bookstatusJson = "{\"userId\":13,\"bookId\":7,\"status\":\"Reading\"}";

    mvc.perform(MockMvcRequestBuilders.post("/api/user-book-status")
        .contentType(MediaType.APPLICATION_JSON)
        .content(bookstatusJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/users/{id}/book-status", 13))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.userId == 13 && @.bookId == 7 && @.status == 'Reading')]", Matchers.hasSize(1)));
  }

  @Test
	void updateBookStatus() throws Exception { 
    String bookstatusJson = "{\"userId\":13,\"bookId\":7,\"status\":\"Finished\"}";

    mvc.perform(MockMvcRequestBuilders.put("/api/user-book-status")
          .contentType(MediaType.APPLICATION_JSON)
          .content(bookstatusJson))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(13))
      .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").value(7))
      .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Finished"));
  }

  @Test
	void deleteBookStatus() throws Exception {
    long userId = 13;
    long bookId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/user-book-status/user/{userId}/book/{bookId}", userId, bookId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
