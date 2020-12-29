package com.zachgoshen.resumebuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ResumeControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeRepository resumeRepository;

    @Test void Get_AllResumes_ReturnsOkWithResumes() throws Exception {
        List<Resume> resumes = new ArrayList<>();
        Resume resume1 = new Resume();
        Resume resume2 = new Resume();
        resume1.setId(1L);
        resume2.setId(2L);
        resumes.add(resume1);
        resumes.add(resume2);

        Mockito.when(resumeRepository.findAll()).thenReturn(resumes);

        mockMvc.perform(get("/resumes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    void Get_ExistingResume_ReturnsOkWithResume() throws Exception {
        Resume resume = new Resume();
        resume.setId(1L);

        Mockito.when(resumeRepository.findById(1L)).thenReturn(Optional.of(resume));

        mockMvc.perform(get("/resumes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void Get_NonExistingResume_ReturnsNoContent() throws Exception {
        Mockito.when(resumeRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/resumes/1"))
                .andExpect(status().isNoContent());
    }

}
