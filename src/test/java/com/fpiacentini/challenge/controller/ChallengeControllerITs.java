package com.fpiacentini.challenge.controller;

import com.fpiacentini.challenge.ChallengeApplication;
import jakarta.servlet.ServletException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ChallengeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-its.properties")
public class ChallengeControllerITs {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenValidParameters_whenGetHistoricApiCalls_shouldReturn200() throws Exception {
        mvc.perform(get("/challenge?pageNumber=0&pageSize=20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenInvalidPageSize_whenGetHistoricApiCalls_shouldThrowException() throws Exception {
        Assert.assertThrows(ServletException.class, () ->
                mvc.perform(get("/challenge?pageNumber=0&pageSize=0")
                        .contentType(MediaType.APPLICATION_JSON)));
    }

    @Test
    public void givenInvalidPageNumber_whenGetHistoricApiCalls_shouldThrowException() throws Exception {
        Assert.assertThrows(ServletException.class, () ->
                mvc.perform(get("/challenge?pageNumber=-1&pageSize=20")
                        .contentType(MediaType.APPLICATION_JSON)));
    }

    @Test
    public void givenValidParameters_whenCalculateResult_shouldReturn200() throws Exception {
        mvc.perform(post("/challenge").content("{\n" +
                                "    \"firstNumber\" : 33,\n" +
                                "    \"secondNumber\" : 4\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
