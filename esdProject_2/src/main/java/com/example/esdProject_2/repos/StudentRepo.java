package com.example.esdProject_2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.esdProject_2.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    @Modifying
    @Query("update Student s set s.isRequestActive = true where s.student_id = :id")
    int markRequestActive(@Param("id") Integer id);

    @Modifying
    @Query("update Student s set s.isRequestActive = false where s.student_id = :id")
    int markRequestInactive(@Param("id") Integer id);

    Optional<Student> findByEmail(String email);
}
