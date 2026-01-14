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
class FavouriteBookTests {

  @Autowired
  MockMvc mvc;

  @Test
	void addFavouriteBook() throws Exception {
    String favouritebookJson = "{\"userId\":13,\"bookId\":7}";

    mvc.perform(MockMvcRequestBuilders.post("/api/favourite-book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(favouritebookJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      // check if book exists in a list of favourite books from userId 13
      mvc.perform(MockMvcRequestBuilders.get("/api/favourite-book/user/{userId}", 13))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasItem(7)));
  }

  @Test
	void deleteFavouriteBook() throws Exception {
    long userId = 13;
    long bookId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/favourite-book/user/{userId}/book/{bookId}", userId, bookId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
