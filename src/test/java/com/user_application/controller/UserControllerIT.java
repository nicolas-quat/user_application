package com.user_application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn400IfInputsAreNotValid() throws Exception {
        // Case with empty name
        String json = """
                {
                    "id": 1,
                    "name": "",
                    "birthdate": 812733225000,
                    "country": "France",
                    "phone": "0685478515",
                    "gender": 1
                }
                """;

        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));

        // Case without birthdate
        json = """
                {
                    "id": 1,
                    "name": "Nicolas",
                    "country": "France",
                    "phone": "0685478515",
                    "gender": 1
                }
                """;

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Birthdate is required and must be in the past"));

        // Case with birthdate in the future
        json = """
                {
                    "id": 1,
                    "name": "Nicolas",
                    "birthdate": 7282182590000,
                    "country": "France",
                    "phone": "0685478515",
                    "gender": 1
                }
                """;

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("must be a past date"));
    }


    @Test
    void shouldCreateUser() throws Exception {
        String json = """
            {
              "id": "1",
              "name": "Nicolas",
              "birthdate": "812733225000",
              "country": "France",
              "phone": "0685478515",
              "gender": 0
            }
            """;

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Nicolas"))
                .andExpect(jsonPath("$.birthdate").value("1995-10-03T15:13:45.000+00:00"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.phone").value("0685478515"))
                .andExpect(jsonPath("$.gender").value("MALE"))
        ;
    }

}
