package com.zachgoshen.resumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired ResumeService resumeService;

    @GetMapping("")
    public List<Resume> list() {
        return resumeService.listAll();
    }

}
