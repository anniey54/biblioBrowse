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
class CollectionBookTests {

  @Autowired
  MockMvc mvc;

  @Test
	void addCollectionBook() throws Exception {
    String collectionbookJson = "{\"collectionId\":7,\"bookId\":7}";

    mvc.perform(MockMvcRequestBuilders.post("/api/collection-book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(collectionbookJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      // check if book exists in a list of books from collection id 7
      mvc.perform(MockMvcRequestBuilders.get("/api/collection-book/collection/{collectionId}", 7))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasItem(7)));
  }

  @Test
	void deleteCollectionBook() throws Exception {
    long collectionId = 7;
    long bookId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/collection-book/collection/{collectionId}/book/{bookId}", collectionId, bookId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
