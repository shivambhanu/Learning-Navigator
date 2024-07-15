package com.backend.navigator.service;

import com.backend.navigator.entity.Exam;
import com.backend.navigator.entity.Student;
import com.backend.navigator.entity.Subject;
import com.backend.navigator.repository.ExamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @InjectMocks
    private ExamService examService;


    @Test
    @DisplayName("first test")
    public void testGetAllExams(){
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertEquals(0, (examService.getAllExam()).size());
    }

    @Test
    @DisplayName("second test")
    public void testGetSpecificExam(){
        Exam tempExamObject = new Exam();
        when(examRepository.findById(any(Long.class))).thenReturn(Optional.of(tempExamObject));

        Assertions.assertEquals(Optional.of(tempExamObject), examRepository.findById(7L));
    }


    @Test
    @DisplayName("third test")
    public void testRegisterStudent(){
        Student tempStudent = new Student();
        List<Student> tempStudentList = Collections.emptyList();
        Subject tempSubject = new Subject(1L, "Mathematics", tempStudentList);
        Exam expectedExam = new Exam(1L, tempSubject, tempStudentList);
        when(examRepository.findById(any(Long.class))).thenReturn(Optional.of(expectedExam));

//        when(examRepository.save(any(Exam.class))).thenReturn(expectedExam);

        Exam actualExam = examService.registerStudentForExam(1L, tempStudent);

        Assertions.assertEquals(expectedExam, actualExam);
        verify(examRepository, times(0)).save(any(Exam.class));
    }
}
