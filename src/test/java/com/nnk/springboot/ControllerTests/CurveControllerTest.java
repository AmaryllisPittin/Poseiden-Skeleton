package com.nnk.springboot.ControllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;

@WebMvcTest(CurveController.class)
@WithMockUser
public class CurveControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CurveService curveService;

        @Test
        public void homeTest() throws Exception {
                CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

                when(curveService.findAll())
                                .thenReturn(List.of(curvePoint));

                mockMvc.perform(get("/curvePoint/list"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("curvePoint/list"))
                                .andExpect(model().attributeExists("curvePoints"));

                verify(curveService).findAll();
        }

        @Test
        public void addFormTest() throws Exception {
                mockMvc.perform(post("/curvePoint/validate")
                                .with(csrf())
                                .param("curveId", "10")
                                .param("term", "10")
                                .param("value", "30"))
                                .andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/curvePoint/list"));

                verify(curveService).save(any(CurvePoint.class));
        }

        @Test
        public void showUpdateFormTest() throws Exception {
                CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

                when(curveService.findById(1))
                                .thenReturn(curvePoint);

                mockMvc.perform(get("/curvePoint/update/1"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("curvePoint/update"))
                                .andExpect(model().attributeExists("curvePoint"));

                verify(curveService).findById(1);
        }

        @Test
        public void deleteTest() throws Exception {

                mockMvc.perform(get("/curvePoint/delete/1")
                                .with(csrf()))
                                .andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/curvePoint/list"));

                verify(curveService).deleteById(1);
        }

}
