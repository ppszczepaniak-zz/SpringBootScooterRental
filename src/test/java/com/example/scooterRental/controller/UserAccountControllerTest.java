package com.example.scooterRental.controller;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //jUnit 4 adnotation
@SpringBootTest  //so it uses all Spring functionalities
@AutoConfigureMockMvc //add to use MockMvc

public class UserAccountControllerTest {

    @Autowired  //injects MockMvc
    private MockMvc mockMvc;

    @Test
    public void ifCreateAccountRequestContainsWrongEmailAddressShouldReturnHttpCode400AndErrorMsg() throws Exception {
        mockMvc.perform(
                post("/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"ownerAge\": 22,\n" +
                                "\t\"ownerEmail\": \"janekexample.com\",\n" +
                                "\t\"ownerUsername\": \"name\"\n}") //e-mail without "@"
        )
                .andExpect(status().is(400))
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR002\",\n" +
                        "\t\"errorMsg\": \"Podaj poprawny adres e-mail.\",\n" +
                        "\t\"status\": \"ERROR\"\n}"))
                .andDo(MockMvcResultHandlers.print()); //andDo() allows to add functionality e.g. with print() it will list details of request
    }



}
