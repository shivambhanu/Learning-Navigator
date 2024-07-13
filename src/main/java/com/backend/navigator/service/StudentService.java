package com.backend.navigator.service;

import com.backend.navigator.entity.Student;
import com.backend.navigator.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            return student.get();
        }else{
            throw new EntityNotFoundException("Student not found with id: " + studentId);
        }
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student student){
        Student prevStudent = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        prevStudent.setName(student.getName());
        prevStudent.setExamList(student.getExamList());
        prevStudent.setSubjectList(student.getSubjectList());

        return studentRepository.save(prevStudent);
    }

    public void deleteStudent(Long studentId){
        studentRepository.deleteById(studentId);
    }

}
