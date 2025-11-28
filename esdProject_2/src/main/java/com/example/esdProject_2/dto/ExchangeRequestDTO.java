package com.example.esdProject_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRequestDTO {
    @JsonProperty("requester_id")
    @NotNull
    private Integer requesterId;
    @JsonProperty("target_student_id")
    @NotNull
    private Integer targetStudentId;
}
