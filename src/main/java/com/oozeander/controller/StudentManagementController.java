package com.oozeander.controller;

import com.oozeander.model.Student;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Billel KETROUCI"),
            new Student(2, "El Bakay"),
            new Student(3, "Meriem KECHEROUD")
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public void registerStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("/{studentId}")
    @Secured({"ROLE_ADMIN"})
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
    }

    @PutMapping("/{studentId}")
    @Secured({"ROLE_ADMIN"})
    public void updateStudent(@PathVariable("studentId") Integer studentId,
                              @RequestBody Student student) {
        System.out.println(studentId + " => " + student);
    }
}