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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@WebMvcTest(BidListController.class)
@WithMockUser
public class BidListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @Test
    public void homeTest() throws Exception {
        BidList bidList = new BidList();

        when(bidListService.findAll())
                .thenReturn(List.of(bidList));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));

        verify(bidListService).findAll();
    }

    @Test
    public void addFormTest() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                .with(csrf())
                .param("bidListId", "10")
                .param("term", "10")
                .param("value", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService).save(any(BidList.class));
    }

    @Test

    public void showUpdateFormTest() throws Exception {
        BidList bidList = new BidList();

        when(bidListService.findById(1))
                .thenReturn(bidList);

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));

        verify(bidListService).findById(1);
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(get("/bidList/delete/1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService).deleteById(1);
    }

}
