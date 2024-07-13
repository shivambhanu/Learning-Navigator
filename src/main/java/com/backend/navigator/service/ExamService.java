package com.backend.navigator.service;

import com.backend.navigator.entity.Exam;
import com.backend.navigator.repository.ExamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExam(){
        return examRepository.findAll();
    }

    public Exam getExamById(Long examId){
        return examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Exam not found with id: " + examId));
    }

    public Exam postExam(Exam exam){
        return examRepository.save(exam);
    }

    public Exam updateExam(Long examId, Exam exam){
        Exam prevExam = examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Exam not found with id: " + examId));

        prevExam.setSubject(exam.getSubject());
        prevExam.setStudentList(exam.getStudentList());

        return examRepository.save(prevExam);
    }

    public void deleteExam(Long examId){
        examRepository.deleteById(examId);
    }
}
