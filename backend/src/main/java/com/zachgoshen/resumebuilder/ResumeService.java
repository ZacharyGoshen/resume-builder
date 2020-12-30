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

    public Resume create(Resume resume) {
        return repository.save(resume);
    }

    public Optional<Resume> update(long id, Resume updatedResume) {
        Optional<Resume> resumeOptional = repository.findById(id);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            resume.setFirstName(updatedResume.getFirstName());
            resume.setLastName(updatedResume.getLastName());
            resume.setAddress(updatedResume.getAddress());
            resume.setCity(updatedResume.getCity());
            resume.setState(updatedResume.getState());
            resume.setPhoneNumber(updatedResume.getPhoneNumber());
            resume.setEmail(updatedResume.getEmail());
            return Optional.of(repository.save(resume));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(long id) {
        Optional<Resume> resumeOptional = repository.findById(id);
        if (resumeOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
