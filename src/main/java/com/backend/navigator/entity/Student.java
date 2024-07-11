package com.backend.navigator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String registrationId;
    private String name;
    private List<Subject> subjectList;
    private List<Exam> examList;
}
