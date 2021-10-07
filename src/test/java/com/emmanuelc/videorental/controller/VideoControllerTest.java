package com.emmanuelc.videorental.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VideoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFetchAtLeastFiveVideosByDefault() throws Exception {
        mockMvc.perform(get("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content", hasSize(5)))
                .andExpect(jsonPath("$.data.pageable.pageSize").value(5))
                .andExpect(jsonPath("$.data.pageable.pageNumber").value(0));
    }

    @Test
    public void shouldPaginateWhenPaginationParamsAreUsed() throws Exception {
        mockMvc.perform(get("/videos")
                        .queryParam("page", "1")
                        .queryParam("size", "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content", hasSize(4)))
                .andExpect(jsonPath("$.data.pageable.pageSize").value(4))
                .andExpect(jsonPath("$.data.pageable.pageNumber").value(1));
    }

}