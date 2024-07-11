package com.backend.navigator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private String examId;
    private Subject subject;
    List<Student> studentList;
}
