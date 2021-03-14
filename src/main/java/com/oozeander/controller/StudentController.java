package com.oozeander.controller;

import com.oozeander.model.Student;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin
public class StudentController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Billel KETROUCI"),
            new Student(2, "El Bakay"),
            new Student(3, "Meriem KECHEROUD")
    );

    @GetMapping("/{studentId}")
    @Secured({"ROLE_STUDENT"})
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream().filter(student -> student.getStudentId().equals(studentId)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Student N°" + studentId + " does not exist"));
    }
}