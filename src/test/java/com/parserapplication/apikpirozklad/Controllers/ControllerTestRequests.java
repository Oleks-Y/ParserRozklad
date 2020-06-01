package com.parserapplication.apikpirozklad.Controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTestRequests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindGroupAndLessonsById() throws Exception {
        this.mockMvc.perform(get("/getGroupAndLessonsById/467")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNegativeFindGroupAndLessonsById() throws Exception {
        this.mockMvc.perform(get("/getGroupAndLessonsById/22222222222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindGroupAndLessonsByName() throws Exception {
        this.mockMvc.perform(get("/getGroupAndLessonsByName/ІВ-82")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNegativeFindGroupAndLessonsByName() throws Exception {
        this.mockMvc.perform(get("/getGroupAndLessonsByName/ІВ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllGroups() throws Exception {
        this.mockMvc.perform(get("/getAllGroups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
