package com.zachgoshen.resumebuilder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResumeControllerTests {

    @Test
    public void FindById_ResumeFound_ReturnsFoundResume() {
        Resume resume = new Resume();
        resume.setId(1);

        ResumeService resumeService = mock(ResumeService.class);
        when(resumeService.findById(1)).thenReturn(Optional.of(resume));

        ResumeController resumeController = new ResumeController(resumeService);
        ResponseEntity<Resume> responseEntity = resumeController.findById(1L);
        assertEquals(responseEntity.getBody().getId(), 1L);
    }

    @Test
    public void FindById_ResumeNotFound_ReturnsNoContentStatus() {
        ResumeService resumeService = mock(ResumeService.class);
        when(resumeService.findById(1)).thenReturn(Optional.empty());

        ResumeController resumeController = new ResumeController(resumeService);
        ResponseEntity<Resume> responseEntity = resumeController.findById(1L);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
