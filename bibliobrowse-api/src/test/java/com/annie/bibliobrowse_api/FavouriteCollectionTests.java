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
class FavouriteCollectionTests {

  @Autowired
  MockMvc mvc;

  @Test
	void addFavouriteCollection() throws Exception {
    String favouritecollectionJson = "{\"userId\":13,\"collectionId\":7}";

    mvc.perform(MockMvcRequestBuilders.post("/api/favourite-collection")
        .contentType(MediaType.APPLICATION_JSON)
        .content(favouritecollectionJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      // check if collection exists in a list of favourite collections from userId 13
      mvc.perform(MockMvcRequestBuilders.get("/api/favourite-collection/user/{userId}", 13))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasItem(7)));
  }

  @Test
	void deleteFavouriteCollection() throws Exception {
    long userId = 13;
    long collectionId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/favourite-collection/user/{userId}/collection/{collectionId}", userId, collectionId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
