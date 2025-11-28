package com.example.esdProject_2.service;

import com.example.esdProject_2.entity.Student;
import com.example.esdProject_2.repos.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class StudentService {

    private final StudentRepo repo;
    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudentList() {
        return repo.findAll();
    }
}

