package com.zachgoshen.resumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumeService {

    @Autowired ResumeRepository repository;

    public List<Resume> listAll() {
        return (List<Resume>) repository.findAll();
    }

    public Optional<Resume> findById(long id) {
        return repository.findById(id);
    }

}
