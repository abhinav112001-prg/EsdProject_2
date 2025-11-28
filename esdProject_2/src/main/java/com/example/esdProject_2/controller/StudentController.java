package com.example.esdProject_2.controller;

import java.util.*;
import com.example.esdProject_2.entity.Student;
import com.example.esdProject_2.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public List<Student> getAllStudentsList(){
        return service.getAllStudentList();
    }

}
