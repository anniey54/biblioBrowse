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
class CollectionTests {

  @Autowired
  MockMvc mvc;

  @Test
	void getCollectionById() throws Exception {
    long collectionId = 1;

    mvc.perform(MockMvcRequestBuilders.get("/api/collections/{id}", collectionId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(collectionId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.creator").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Public"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("My Personal Book Collection: A Handpicked Collection of Beloved Reads"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.summary").value("Welcome to a deeply personal exploration of my favourite literary works. \n    My taste tends to gravitate towards thought-provoking literary fiction, insightful historical accounts, and thrilling mysteries that keep me guessing. You'll also find a fair share of character-driven sagas and well-crafted science fiction that explore complex ideas. \n    Each book here holds a special significance, representing moments of revelation, joy, solace, or pure escapism. I often revisit these cherished volumes, finding new insights with every read, and they are the first ones I recommend when asked for a truly great book. Dive in and discover the stories that have shaped my journey as a reader, and perhaps find a new favourite to add to your own collection."))
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfLikes").value(5))
      .andExpect(MockMvcResultMatchers.jsonPath("$.genres", Matchers.containsInAnyOrder("Adventure", 
                                                                                                            "Romance", "Action", 
                                                                                                            "Dystopia", "Science_fiction")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value("2025-09-26"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.lastUpdatedAt").value("2025-09-26"));
  }

  @Test
	void addCollection() throws Exception {
    String collectionJson = "{\"creator\":13,\"status\":\"Private\",\"title\":\"Test collection 1\",\"summary\":\"This is a summary for collection 1\",\"genres\":[\"Action\",\"Urban\",\"Male_protagonist\"]}";

    mvc.perform(MockMvcRequestBuilders.post("/api/collections")
        .contentType(MediaType.APPLICATION_JSON)
        .content(collectionJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/collections"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.creator == 13 && @.title == 'Test collection 1' && @.summary == 'This is a summary for collection 1')]", Matchers.hasSize(1)));
  }

  @Test
	void updateCollection() throws Exception {
    String collectionJson = "{\"id\":7,\"creator\":13,\"status\":\"Public\",\"title\":\"Updated Test collection 1\",\"summary\":\"This is a summary for updated collection 1\",\"numberOfLikes\":1,\"genres\":[\"Action\",\"Urban\",\"Male_protagonist\",\"Romance\"],\"createdAt\": \"2025-12-03\"}";

    mvc.perform(MockMvcRequestBuilders.put("/api/collections")
          .contentType(MediaType.APPLICATION_JSON)
          .content(collectionJson))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(7))
      .andExpect(MockMvcResultMatchers.jsonPath("$.creator").value(13))
      .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Public"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Test collection 1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.summary").value("This is a summary for updated collection 1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfLikes").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.genres", Matchers.containsInAnyOrder("Action", 
                                                                                                            "Urban", "Male_protagonist", 
                                                                                                            "Romance")));
  }

  @Test
	void deleteCollection() throws Exception {
    long collectionId = 7;
    mvc.perform(MockMvcRequestBuilders.delete("/api/collections/{id}", collectionId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
