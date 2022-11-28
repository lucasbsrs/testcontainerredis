package com.lucas.testcontainerredis.person;

import com.lucas.testcontainerredis.IntegrationTest;
import com.lucas.testcontainerredis.entities.Person;
import com.lucas.testcontainerredis.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("List of person")
    void listOfPerson() throws Exception {
        var joao = new Person("João", 22);
        var maria = new Person("Maria", 20);
        personRepository.save(joao);
        personRepository.save(maria);

        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Create person")
    void createPerson() throws Exception {
        var objectMapper = new ObjectMapper();
        var joao = new Person("João", 40);

        mvc.perform(MockMvcRequestBuilders.
                        post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(joao))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.age").value(40));
    }

    @Test
    @DisplayName("Delete person")
    void deletePerson() throws Exception {
        var joao = new Person("João", 10);
        personRepository.save(joao);

        mvc.perform(MockMvcRequestBuilders.
                        delete("/person/" + joao.getId())
                )
                .andExpect(status().isOk());
    }

}
