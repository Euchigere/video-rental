package com.emmanuelc.videorental.controller;

import com.emmanuelc.videorental.domain.dto.RentRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RentDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    void checkVideoRentRequest(long videoId, String username, int noOfDays) throws Exception {
        final RentRequestDto rentRequestDto = new RentRequestDto()
                .setVideoId(videoId)
                .setUsername(username)
                .setNoOfDays(noOfDays);

        mockMvc.perform(post("/rents")
                .content(mapper.writeValueAsBytes(rentRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(username))
                .andExpect(jsonPath("$.data.noOfDays").value(noOfDays))
                .andExpect(jsonPath("$.data.videoTitle").exists())
                .andExpect(jsonPath("$.data.price").exists());
    }

    @Test
    public void shouldProcessVideoRentRequestSuccessfully() throws Exception {
        checkVideoRentRequest(1, "user", 20);
        checkVideoRentRequest(4, "Fisayo", 5);
        checkVideoRentRequest(7, "Busayo", 3);
    }

    @Test void shouldFailIfRequestConstraintsAreViolated() throws Exception {
        final RentRequestDto rentRequestDto = new RentRequestDto()
                .setUsername("")
                .setNoOfDays(0);

        mockMvc.perform(post("/rents")
                        .content(mapper.writeValueAsBytes(rentRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.data.videoId").value("must not be null"))
                .andExpect(jsonPath("$.data.noOfDays").value("must be greater than or equal to 1"))
                .andExpect(jsonPath("$.data.username").value("must not be blank"));
    }

}