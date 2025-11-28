package com.example.esdProject_2.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ExchangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "requester_id"))
    private Student requester;

    @ManyToOne
    @JoinColumn(name = "target_student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "target_student_id"))
    private Student target_student;

    public enum ExchangeStatus {NEW, APPROVED, REJECTED}
    @Enumerated(EnumType.STRING)
    private ExchangeStatus status = ExchangeStatus.NEW;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "datetime default current_timestamp")
    private Date request_date;

    public ExchangeRequest(Student requester, Student target_student) {
        this.requester = requester;
        this.target_student = target_student;
        this.request_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(request_date);
    }
}
/*
    +-------------------+-----------------------------------+------+-----+-------------------+-------------------+
    | Field             | Type                              | Null | Key | Default           | Extra             |
    +-------------------+-----------------------------------+------+-----+-------------------+-------------------+
    | id                | int                               | NO   | PRI | NULL              | auto_increment    |
    | requester_id      | int                               | NO   | MUL | NULL              |                   |
    | target_student_id | int                               | NO   | MUL | NULL              |                   |
    | status            | enum('NEW','APPROVED','REJECTED') | YES  |     | NEW               |                   |
    | request_date      | datetime                          | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    +-------------------+-----------------------------------+------+-----+-------------------+-------------------+
*/