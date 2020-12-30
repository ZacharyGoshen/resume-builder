package com.zachgoshen.resumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumeService {

    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public List<Resume> listAll() {
        return (List<Resume>) resumeRepository.findAll();
    }

    public Optional<Resume> findById(long id) {
        return resumeRepository.findById(id);
    }

    public Resume create(Resume resume) {
        return resumeRepository.save(resume);
    }

    public Optional<Resume> update(long id, Resume updatedResume) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            resume.setFirstName(updatedResume.getFirstName());
            resume.setLastName(updatedResume.getLastName());
            resume.setAddress(updatedResume.getAddress());
            resume.setCity(updatedResume.getCity());
            resume.setState(updatedResume.getState());
            resume.setPhoneNumber(updatedResume.getPhoneNumber());
            resume.setEmail(updatedResume.getEmail());
            return Optional.of(resumeRepository.save(resume));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(long id) {
        Optional<Resume> resumeOptional = resumeRepository.findById(id);
        if (resumeOptional.isPresent()) {
            resumeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
