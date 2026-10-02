package com.nnk.springboot.ControllerTests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(TradeController.class)
@WithMockUser
public class TradeControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private TradeService tradeService;

        @Test
        public void homeTest() throws Exception {
                Trade trade = new Trade();

                when(tradeService.findAll())
                                .thenReturn(List.of(trade));

                mockMvc.perform(get("/trade/list"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("trade/list"))
                                .andExpect(model().attributeExists("trades"));

                verify(tradeService).findAll();
        }

        @Test
        public void addFormTest() throws Exception {
                mockMvc.perform(get("/trade/add"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("trade/add"));
        }

        @Test
        public void showUpdateFormTest() throws Exception {
                Trade trade = new Trade();

                when(tradeService.findById(1))
                                .thenReturn(trade);

                mockMvc.perform(get("/trade/update/1")
                                .with(csrf()))
                                .andExpect(status().isOk())
                                .andExpect(view().name("trade/update"))
                                .andExpect(model().attributeExists("trade"));

                verify(tradeService).findById(1);
        }

        @Test
        public void deleteTest() throws Exception {

                mockMvc.perform(get("/trade/delete/1")
                                .with(csrf()))
                                .andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/trade/list"));

                verify(tradeService).deleteById(1);
        }

}
