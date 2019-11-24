package com.example.scooterRental.controller;

import com.example.scooterRental.api.response.CreateUserAccountResponse;
import com.example.scooterRental.model.UserAccount;
import com.example.scooterRental.repository.UserAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) //in jUnit 4 it was @RunWith(SpringRunner.class)
@SpringBootTest  //so it uses all Spring functionalities
@AutoConfigureMockMvc //add to use MockMvc

public class UserAccountControllerTest {

    @Autowired  //injects MockMvc
    private MockMvc mockMvc;

    @Autowired
    UserAccountRepository userAccountRepository;

    @ParameterizedTest
    @ValueSource(strings = {
            "janekexample.com",
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            "あいうえお@example.com",
            "email@example.com (Joe Smith)",
            "email @gmail.com"
    })
    public void ifCreateAccountRequestContainsWrongEmailAddressShouldReturnHttpCode400AndErrorMsg(String wrongEmail) throws Exception {
        mockMvc.perform(
                post("/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"ownerAge\": 22,\n" +
                                "\t\"ownerEmail\": \"" + wrongEmail + "\",\n" + //wrong email here
                                "\t\"ownerUsername\": \"name\"\n}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR002\",\n" +
                        "\t\"errorMsg\": \"Podaj poprawny adres e-mail.\",\n" +
                        "\t\"status\": \"ERROR\"\n}"))
                .andDo(MockMvcResultHandlers.print()); //andDo() allows to add functionality e.g. with print() it will list details of request
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 29141, 0, 101, -12321})
    public void ifCreateAccountRequestContainsWrongAgeShouldReturnHttpCode400AndErrorMsg(int wrongAge) throws Exception {
        mockMvc.perform(
                post("/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"ownerAge\":" + wrongAge + ",\n" + //wrong age here
                                "\t\"ownerEmail\": \"johndoe@gmail.com\",\n" +
                                "\t\"ownerUsername\": \"name\"\n}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR003\",\n" +
                        "\t\"errorMsg\": \"Wiek powinien mieścić się w zakresie od 1 do 100.\",\n" +
                        "\t\"status\": \"ERROR\"\n}"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/correctUsers.csv")
    public void ifCreateAccountRequestContainsCorrectDataShouldReturnHttpCode200AndOKMsg(int age, String correctEmail, String correctName) throws Exception {
        MvcResult myMvcRequestResult = mockMvc.perform(
                post("/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"ownerAge\": " + age + ",\n" +
                                "\t\"ownerEmail\": \"" + correctEmail + "\",\n" +
                                "\t\"ownerUsername\": \"" + correctName + "\"\n}"))
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.containsString("Poprawnie utworzono konto użytkownika.")))
                .andExpect(content().string(Matchers.containsString("SUCCESS")))
                .andExpect(content().string(Matchers.containsString("accountId")))
                .andReturn(); //returns object which represents response

        ObjectMapper objectMapper = new ObjectMapper(); //ObjectMapper creates java object from JSON
        CreateUserAccountResponse response = objectMapper.readValue(
                myMvcRequestResult.getResponse().getContentAsString(), CreateUserAccountResponse.class  //inflates CreateUserAccountResponse.class with JSON data
        );

        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(response.getAccountId()); /*we check if account was really added - we find it in DB
                                                                                                               optional makes sure that object is non-null*/
        assertTrue(optionalUserAccount.isPresent());  //isPresent() returns true if optional contains object
    }

}
