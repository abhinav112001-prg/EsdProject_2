package com.example.esdProject_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PendingRequestDTO {
    @JsonProperty("student_id")
    @NotNull
    private Integer studentId;
}
