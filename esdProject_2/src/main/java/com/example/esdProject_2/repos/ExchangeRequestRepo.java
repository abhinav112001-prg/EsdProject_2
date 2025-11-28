package com.example.esdProject_2.repos;

import com.example.esdProject_2.dto.UpdateExchangeRequestDTO;
import com.example.esdProject_2.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRequestRepo extends JpaRepository<ExchangeRequest, Integer> {
    @Modifying
    @Query("update ExchangeRequest e set e.status = :#{#dto.status} where e.id = :#{#dto.requestId}")
    public int updateExchangeRequest(@Param("dto") UpdateExchangeRequestDTO dto);

    @Query("select e from ExchangeRequest e where e.target_student.student_id = :studentId and e.status = com.example.esdProject_2.entity.ExchangeRequest.ExchangeStatus.NEW")
    List<ExchangeRequest> findAllByTargetId(@Param("studentId") Integer studentId);

    @Query("select e from ExchangeRequest e where e.requester.student_id = :studentId")
    List<ExchangeRequest> findAllByRequesterId(@Param("studentId") Integer studentId);
}
