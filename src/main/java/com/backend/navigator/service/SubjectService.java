package com.backend.navigator.service;

import com.backend.navigator.entity.Subject;
import com.backend.navigator.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;


    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long subjectId){
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
        return subject;
    }


    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long subjectId, Subject subject){
        Subject prevSubject = subjectRepository.findById(subjectId).orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));

        prevSubject.setSubjectName(subject.getSubjectName());
        prevSubject.setStudentList(subject.getStudentList());

        return subjectRepository.save(prevSubject);
    }

    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }
}
