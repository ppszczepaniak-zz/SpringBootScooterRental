package com.example.scooterRental.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class) //in jUnit 4 it was @RunWith(SpringRunner.class)
@SpringBootTest  //so it uses all Spring functionalities
@AutoConfigureMockMvc //add to use MockMvc
public class ScooterDockControllerTest {

    @Autowired  //injects MockMvc
    private MockMvc mockMvc;

    //check if getScooters works properly for dockID = 1
    @Test
    public void ifGetScootersRequestIs1ShouldReturnHttpCode200AndInitialScooterList() throws Exception {
        mockMvc.perform(get("/scooter-dock/{scooterDockId}/scooters", 1))     //1 = placeholder for {scooterDockId}
                .andExpect(status().is(200))
                .andExpect(content().json(
                        "[{\n" +  //JSON from SwaggerUI, formatted via https://onlinestringtools.com/convert-json-to-string + escaped
                                "\t\"id\": 5,\n" +
                                "\t\"modelName\": \"ERE-321\",\n" +
                                "\t\"maxSpeed\": 25,\n" +
                                "\t\"rentalPrice\": 19.99\n" +
                                "},\n  " +
                                "{\n" +
                                "\t\"id\": 8,\n" +
                                "\t\"modelName\": \"V-SPEED-2\",\n" +
                                "\t\"maxSpeed\": 40,\n" +
                                "\t\"rentalPrice\": 39.99\n" +
                                "},\n  " +
                                "{\n" +
                                "\t\"id\": 6,\n" +
                                "\t\"modelName\": \"RTT-43z\",\n" +
                                "\t\"maxSpeed\": 20,\n" +
                                "\t\"rentalPrice\": 15.5\n" +
                                "},\n" +
                                "{\n" +
                                "\t\"id\": 7,\n" +
                                "\t\"modelName\": \"V-SPEED-1\",\n" +
                                "\t\"maxSpeed\": 35,\n" +
                                "\t\"rentalPrice\": 29.99\n" +
                                "}]"
                ));
    }


    //check if getScooters works properly for dockID = 2,3,4
    @ParameterizedTest
    @CsvFileSource(resources = "/scooterDockIdAndJSONresult.csv")
    public void ifGetScootersRequestIsCorrectShouldReturnHttpCode200AndInitialScooterList(int dockId, String JsonInCSV) throws Exception {
        mockMvc.perform(get("/scooter-dock/{scooterDockId}/scooters", dockId))     //1 = placeholder for {scooterDockId}
                .andExpect(status().is(200))
                .andExpect(content().json(JsonInCSV
                ));
    }

    //wrong DockIDs
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 9999, 568907690})
    public void ifGetScootersRequestContainsDockIdWhichDoesNotExistShouldReturnCode409AndErrorMsg(int wrongId) throws Exception {
        mockMvc.perform(get("/scooter-dock/{scooterDockId}/scooters", wrongId))
                .andExpect(status().is(409))
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR008\",\n" +
                        "\t\"errorMsg\": \"Dok o podanym id nie istnieje.\",\n" +
                        "\t\"status\": \"ERROR\"\n}"));
    }


}
