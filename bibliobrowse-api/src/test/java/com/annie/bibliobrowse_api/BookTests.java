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
class BookTests {

  @Autowired
  MockMvc mvc;

  @Test
	void getBookById() throws Exception {
    long bookId = 2;

    mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Hunger Games"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.genres", Matchers.containsInAnyOrder("Action", "Adventure", 
                                                                                                            "Fantasy", "Thriller", 
                                                                                                            "Science_fiction", "Horror", 
                                                                                                            "Psychological", "Dystopia", 
                                                                                                            "Female_protagonist")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.summary").value("Winning means fame and fortune. Losing means certain death. The Hunger Games have begun. . . .\n    In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. The Capitol is harsh and cruel and keeps the districts in line by forcing them all to send one boy and one girl between the ages of twelve and eighteen to participate in the annual Hunger Games, a fight to the death on live TV.\n    Sixteen-year-old Katniss Everdeen regards it as a death sentence when she steps forward to take her sister's place in the Games. But Katniss has been close to dead before-and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weigh survival against humanity and life against love."))
      .andExpect(MockMvcResultMatchers.jsonPath("$.audience").value("Young_adult"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("English"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberPage").value(374))
      .andExpect(MockMvcResultMatchers.jsonPath("$.publisher").value("Scholastic Press"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("9780439023481 (ISBN10: 0439023483)"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.series").value("The Hunger Games"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.volume").value(1));
  }

  @Test
	void addBook() throws Exception {
    String bookJson = "{\"title\":\"Test book1\",\"author\":52,\"summary\":\"This is a summary\",\"audience\":\"Middle_grade\",\"language\":\"Spanish\",\"numberPage\":220,\"publisher\":\"Publisher\",\"isbn\":\"9798400903526\"}";

    mvc.perform(MockMvcRequestBuilders.post("/api/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content(bookJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/books"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.title == 'Test book1' && @.author == 52)]", Matchers.hasSize(1)));
  }

  @Test
	void addBook2() throws Exception {
    String bookJson = "{\"title\":\"Test book2\",\"author\":52,\"summary\":\"This is a summary for book 2\",\"genres\":[\"Action\",\"Religious\",\"Crime\",\"Poetry\",\"Short_stories\"],\"audience\":\"Adult\",\"language\":\"Spanish\",\"numberPage\":220,\"publisher\":\"Publisher\",\"isbn\":\"9798400903526\"}";

    mvc.perform(MockMvcRequestBuilders.post("/api/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content(bookJson))
      .andExpect(MockMvcResultMatchers.status().isOk());

      mvc.perform(MockMvcRequestBuilders.get("/api/books"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.title == 'Test book2' && @.author == 52 && @.isbn == '9798400903526')]", Matchers.hasSize(1)));
  }

  @Test
	void updateBook() throws Exception {
    String bookJson = "{\"id\":7,\"title\":\"Updated Test book2\",\"author\":52,\"summary\":\"This is a summary for updated book 2\",\"genres\":[\"Adventure\",\"Crime\",\"Poetry\",\"Short_stories\"],\"audience\":\"Adult\",\"language\":\"Spanish\",\"numberPage\":2120,\"publisher\":\"Publisher\",\"isbn\":\"9798400903526\"}";

    mvc.perform(MockMvcRequestBuilders.put("/api/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(bookJson))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(7))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Test book2"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(52))
      .andExpect(MockMvcResultMatchers.jsonPath("$.genres", Matchers.containsInAnyOrder("Adventure", "Crime", "Poetry", "Short_stories")))
      .andExpect(MockMvcResultMatchers.jsonPath("$.summary").value("This is a summary for updated book 2"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.audience").value("Adult"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("Spanish"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.numberPage").value(2120))
      .andExpect(MockMvcResultMatchers.jsonPath("$.publisher").value("Publisher"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("9798400903526"));
  }

  @Test
	void deleteBook() throws Exception {
    long bookId = 6;
    mvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
