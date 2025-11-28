package com.example.esdProject_2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.esdProject_2.entity.ExchangeRequest.ExchangeStatus;

@Data
@NoArgsConstructor
public class UpdateExchangeRequestDTO {
    @JsonProperty("request_id") // exchange_request ID, will be coming through list of exchange requests
    @NotNull
    private Integer requestId;

    @JsonProperty("status") // status of exchange request, either APPROVED or REJECTED
    @NotNull
    private ExchangeStatus status;
}
