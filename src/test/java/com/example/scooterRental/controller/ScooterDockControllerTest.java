package com.example.scooterRental.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class) //jUnit 4 adnotation
@SpringBootTest  //so it uses all Spring functionalities
@AutoConfigureMockMvc //add to use MockMvc
public class ScooterDockControllerTest {

    @Autowired  //injects MockMvc
    private MockMvc mockMvc;

    @Test
    public void ifGetScootersRequestIsCorrectShouldReturnHttpCode200AndInitialScooterList() throws Exception {
        mockMvc.perform(null);
    }
}
