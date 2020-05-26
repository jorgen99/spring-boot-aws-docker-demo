package com.jorgenlundberg.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FileIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void it_should_return_CREATED_on_success() throws Exception {
    FileEvent event = new FileEvent("s3://the_bucket/the_path/the_file");
        String eventJson = asJsonString(event);

    mockMvc
        .perform(
            post("/event/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventJson))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().json("{ 'status':'AWESOME', 'message':''}"));
  }

  @Test
  public void it_should_return_OK_hello() throws Exception {
    mockMvc
        .perform(
            get("/")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("{ 'status':'AWESOME', 'message':'Hello World!'}"));
  }

  private String asJsonString(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

}
