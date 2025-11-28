package com.example.esdProject_2.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer floor;
    private String room_number;
    @OneToOne
    @JoinColumn(name = "student_id", unique = true, foreignKey = @ForeignKey(name = "student_id"))
    private Student student;

}
/*
    +-------------+--------------+------+-----+---------+----------------+
    | id          | int          | NO   | PRI | NULL    | auto_increment |
    | name        | varchar(100) | YES  |     | NULL    |                |
    | floor       | int          | YES  |     | NULL    |                |
    | room_number | varchar(10)  | YES  |     | NULL    |                |
    | student_id  | int          | YES  | UNI | NULL    |                |
    +-------------+--------------+------+-----+---------+----------------+
*/