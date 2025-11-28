package com.example.esdProject_2.repos;

import com.example.esdProject_2.entity.Hostel;
import com.example.esdProject_2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface HostelRepo extends JpaRepository<Hostel, Integer> {
    @Modifying
    @Query(value = "update hostel set student_id = null where student_id = :id", nativeQuery = true)
    int clearStudentByStudentId(@Param("id") Integer id);

    @Modifying
    @Query(value = "update hostel set student_id = :newId where student_id = :currentId", nativeQuery = true)
    int setStudentIdWhereStudentId(@Param("currentId") Integer currentId, @Param("newId") Integer newId);

    @Modifying
    @Query(value = "update hostel set student_id = :newId where student_id is null", nativeQuery = true)
    int setStudentIdWhereNull(@Param("newId") Integer newId);

    @Query("select h from Hostel h where h.student.student_id = :studentId")
    Optional<Hostel> findByStudentId(@Param("studentId") Integer studentId);
}
