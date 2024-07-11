package com.backend.navigator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private String subjectId;
    private String subjectName;
    private List<Student> studentList;
}
