package com.zachgoshen.resumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Resume>> list() {
        return new ResponseEntity<>(resumeService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> findById(@PathVariable Long id) {
        Optional<Resume> resume = resumeService.findById(id);
        if (resume.isPresent()) {
            return new ResponseEntity<>(resume.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Resume> create(@RequestBody Resume resume) {
        return new ResponseEntity<>(resumeService.create(resume), HttpStatus.CREATED);
    }
}
