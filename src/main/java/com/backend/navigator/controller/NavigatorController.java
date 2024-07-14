package com.backend.navigator.controller;

import com.backend.navigator.entity.Exam;
import com.backend.navigator.entity.Student;
import com.backend.navigator.entity.Subject;
import com.backend.navigator.service.ExamService;
import com.backend.navigator.service.StudentService;
import com.backend.navigator.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NavigatorController {

    //Autowire your services here
    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamService examService;


    //<============================================= student api's ===============================================>
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //we will use GlobalException handling for all types of exception handling.
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        Student updatedStudent = studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //<============================================= subject api's ===============================================>
    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubject(){
        return new ResponseEntity<>(subjectService.getAllSubject(), HttpStatus.OK);
    }

    @GetMapping("/subjects/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(Long subjectId){
        return new ResponseEntity<>(subjectService.getSubjectById(subjectId), HttpStatus.OK);
    }

    @PostMapping("/subjects")
    public ResponseEntity<Subject> postSubject(@RequestBody Subject subject){
        return new ResponseEntity<>(subjectService.saveSubject(subject), HttpStatus.CREATED);
    }

    @PutMapping("/subjects/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject){
        return new ResponseEntity<>(subjectService.updateSubject(subjectId, subject), HttpStatus.OK);
    }

    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //<============================================= exam api's ===============================================>
    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExam(){
        return new ResponseEntity<>(examService.getAllExam(), HttpStatus.OK);
    }

    @GetMapping("/exams/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId){
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }

    @PostMapping("/exams")
    public ResponseEntity<Exam> postExam(@RequestBody Exam exam){
        return new ResponseEntity<>(examService.postExam(exam), HttpStatus.CREATED);
    }

    @PutMapping("/exams/{examId}")
    public ResponseEntity<Exam> registerStudentForExam(@PathVariable Long examId, @RequestBody Student student){
        return new ResponseEntity<>(examService.registerStudentForExam(examId, student), HttpStatus.OK);
    }

    @DeleteMapping("/exams/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId){
        examService.deleteExam(examId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
