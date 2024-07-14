package com.backend.navigator.service;

import com.backend.navigator.entity.Exam;
import com.backend.navigator.entity.Student;
import com.backend.navigator.entity.Subject;
import com.backend.navigator.repository.ExamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

//    public Exam updateExam(Long examId, Exam exam){
//        Exam prevExam = examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Exam not found with id: " + examId));
//
//        prevExam.setSubject(exam.getSubject());
//        prevExam.setStudentList(exam.getStudentList());
//
//        return examRepository.save(prevExam);
//    }

    public void deleteExam(Long examId){
        examRepository.deleteById(examId);
    }


    //register in an exam, but the student must be registered in the subject of that exam first
    public Exam registerStudentForExam(Long examId, Student student){
//        Student currStudent = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Exam not found with id: " + examId));
        if(student == null)
            throw new NullPointerException("Student data is null");

        Subject subject = exam.getSubject();
        List<Student> studentsEnrolledInSubject = subject.getStudentList();

        boolean isEnrolledInSubject = false;
        for(Student studentObj : studentsEnrolledInSubject){
            if(student.getRegistrationId().equals(studentObj.getRegistrationId())){
                isEnrolledInSubject = true;
                break;
            }
        }

        if(isEnrolledInSubject){
            List<Student> currListOfStudents = exam.getStudentList();
            currListOfStudents.add(student);
            exam.setStudentList(currListOfStudents);
            examRepository.save(exam);
        }

        return exam;
    }
}
