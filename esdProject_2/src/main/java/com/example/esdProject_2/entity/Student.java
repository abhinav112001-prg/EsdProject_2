package com.example.esdProject_2.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    private String roll_number;
    private String name;
    private String email;
    private Double cgpa;
    private Integer total_credits;
    private Integer graduation_year;
    private String domain;
    @Column(name = "is_request_active", nullable = false)
    private Boolean isRequestActive = false;
}
/*
        +-----------------+--------------+------+-----+---------+----------------+
        | student_id      | int          | NO   | PRI | NULL    | auto_increment |
        | roll_number     | varchar(20)  | NO   | UNI | NULL    |                |
        | name            | varchar(100) | YES  |     | NULL    |                |
        | email           | varchar(100) | YES  | UNI | NULL    |                |
        | cgpa            | double       | YES  |     | NULL    |                |
        | total_credits   | int          | YES  |     | NULL    |                |
        | graduation_year | int          | YES  |     | NULL    |                |
        | domain          | varchar(50)  | YES  |     | NULL    |                |
        +-----------------+--------------+------+-----+---------+----------------+
*/