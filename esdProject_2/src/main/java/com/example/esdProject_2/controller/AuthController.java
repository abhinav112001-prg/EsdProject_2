package com.example.esdProject_2.controller;

import com.example.esdProject_2.entity.Hostel;
import com.example.esdProject_2.entity.Student;
import com.example.esdProject_2.repos.HostelRepo;
import com.example.esdProject_2.repos.StudentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StudentRepo studentRepo;
    private final HostelRepo hostelRepo;

    public AuthController(StudentRepo studentRepo, HostelRepo hostelRepo) {
        this.studentRepo = studentRepo;
        this.hostelRepo = hostelRepo;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        // Google returns the email in the "email" attribute
        String email = principal.getAttribute("email");
        Student student = studentRepo.findByEmail(email)
                .orElse(null);

        if (student == null) {
            // Case: User logged in with Google, but is not in your Students table
            return ResponseEntity.status(403).body("Email " + email + " is not registered in the University database.");
        }

        Hostel hostel = hostelRepo.findByStudentId(student.getStudent_id()).orElse(null);
        
        // response map with both student and hostel details
        return ResponseEntity.ok(Map.of(
            "student", student,
            "hostelRoom", hostel
        ));
    }
}