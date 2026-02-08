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
class UserTests {

  @Autowired
  MockMvc mvc;

  @Test
	void getUserById() throws Exception {
    long userId = 2;

    mvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("mary.sue@email.com"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("MarySue"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.salt").value("thisissalt"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password123123"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isAdmin").value(false));
  }

  @Test
	void addUser() throws Exception {
    String userJson = "{\"username\":\"testUser1\",\"email\":\"testuser1@example.com\",\"salt\":\"testsalt\",\"password\":\"testpassword\"}";

    mvc.perform(MockMvcRequestBuilders.post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/users"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$[*].email", Matchers.hasItem("testuser1@example.com")));
  }

  @Test
	void updateUser() throws Exception {
    String userJson = "{\"id\":13,\"username\":\"updatedTestUser1\",\"email\":\"updatedtestuser2@example.com\",\"salt\":\"testsalt\",\"password\":\"testpassword1\",\"isAdmin\": true}";

    mvc.perform(MockMvcRequestBuilders.put("/api/users")
          .contentType(MediaType.APPLICATION_JSON)
          .content(userJson))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(13))
      .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("updatedTestUser1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("updatedtestuser2@example.com"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.salt").value("testsalt"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testpassword1"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isAdmin").value(true));
  }

  @Test
	void deleteUser() throws Exception {
    long userId = 12;
    mvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", userId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
