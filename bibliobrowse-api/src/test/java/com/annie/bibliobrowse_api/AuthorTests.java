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
class AuthorTests {

  @Autowired
  MockMvc mvc;

  @Test
	void getAuthorById() throws Exception {
    long authorId = 1;

    mvc.perform(MockMvcRequestBuilders.get("/api/authors/{id}", authorId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Suzanne Collins"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.about").value("Suzanne Collins is an American author acclaimed for her significant contributions to young adult literature, most notably the globally successful Hunger Games series. Born in Hartford, Connecticut, in 1962, Collins's early life was profoundly shaped by her father's military career and his experiences in the Vietnam War. This background instilled in her a deep understanding of conflict and survival, themes that are central to her most famous works. Before becoming a celebrated novelist, Collins honed her storytelling skills as a television writer for children's shows, including Clarissa Explains It All, which prepared her to craft narratives that resonate with young audiences."))
      .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("August 11, 1962"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.website").value("http://suzannecollinsbooks.com/"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.socialX").value(""))
      .andExpect(MockMvcResultMatchers.jsonPath("$.instagram").value(""));
  }

  @Test
	void addAuthor() throws Exception {
    String authorJson = "{\"fullName\":\"Test Author1\",\"about\":\"This is a test author number 1\",\"dateOfBirth\":\"January 14, 2026\",\"website\":\"websiteHere.com\"}";

    mvc.perform(MockMvcRequestBuilders.post("/api/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(authorJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/authors"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].fullName", Matchers.hasItem("Test Author1")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].about", Matchers.hasItem("This is a test author number 1")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].dateOfBirth", Matchers.hasItem("January 14, 2026")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].website", Matchers.hasItem("websiteHere.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].socialX", Matchers.hasItem("")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].instagram", Matchers.hasItem("")));
  }

  @Test
	void updateAuthor() throws Exception {
    String authorJson = "{\"id\":6,\"fullName\":\"updated TestAuthor1\",\"about\":\"This is an updated test author number 1\",\"dateOfBirth\":\"January 14, 2026\",\"website\":\"websiteHere.com\",\"instagram\": \"instagram.com\", \"socialX\":\"\"}";

    mvc.perform(MockMvcRequestBuilders.put("/api/authors")
          .contentType(MediaType.APPLICATION_JSON)
          .content(authorJson))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(6))
      .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("updated TestAuthor1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.about").value("This is an updated test author number 1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("January 14, 2026"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.website").value("websiteHere.com"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.socialX").value(""))
      .andExpect(MockMvcResultMatchers.jsonPath("$.instagram").value("instagram.com"));
  }

  @Test
	void deleteAuthor() throws Exception {
    long authorId = 8;
    mvc.perform(MockMvcRequestBuilders.delete("/api/authors/{id}", authorId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
