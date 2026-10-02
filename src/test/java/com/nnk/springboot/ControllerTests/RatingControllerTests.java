package com.nnk.springboot.ControllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

public class RatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    public void homeTest() throws Exception {
        Rating rating = new Rating();

        when(ratingService.findAll())
                .thenReturn(List.of(rating));

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("rating"));

        verify(ratingService).findAll();
    }

    @Test
    public void addFormTest() throws Exception {
        mockMvc.perform(post("/rating/validate")
                .with(csrf())
                .param("bidListId", "10")
                .param("term", "10")
                .param("value", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService).save(any(Rating.class));
    }

    @Test
    public void showUpdateFormTest() throws Exception {
        Rating rating = new Rating();

        when(ratingService.findById(1))
                .thenReturn(rating);

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));

        verify(ratingService).findById(1);
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(get("/rating/delete/1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService).deleteById(1);
    }

}
