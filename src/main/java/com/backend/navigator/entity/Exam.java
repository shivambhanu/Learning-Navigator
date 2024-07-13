package com.backend.navigator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    //An exam can only happen for a single subject.
    @ManyToOne
    private Subject subject;

    @ManyToMany
    List<Student> studentList;
}
