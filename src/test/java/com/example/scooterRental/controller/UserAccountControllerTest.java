package com.example.scooterRental.controller;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //jUnit 4 adnotation
@SpringBootTest  //so it uses all Spring functionalities
@AutoConfigureMockMvc //add to use MockMvc

public class UserAccountControllerTest {

    @Autowired  //injects MockMvc
    private MockMvc mockMvc;

//    @Test
//    public void ifGetScootersRequestIsCorrectShouldReturnHttpCode200AndInitialScooterList(int dockId, String escapedJSON) throws Exception {
//        mockMvc.perform(get("/scooter-dock/{scooterDockId}/scooters", dockId))     //1 = placeholder for {scooterDockId}
//                .andExpect(status().is(200))
//                .andExpect(content().json(escapedJSON
//                ));
//    }

}
