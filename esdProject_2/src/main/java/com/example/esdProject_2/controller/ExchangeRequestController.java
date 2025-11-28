package com.example.esdProject_2.controller;

import java.util.*;

import com.example.esdProject_2.dto.ExchangeRequestDTO;
import com.example.esdProject_2.dto.UpdateExchangeRequestDTO;
import com.example.esdProject_2.entity.ExchangeRequest;
import com.example.esdProject_2.service.ExchangeRequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRequestController {

    private final ExchangeRequestService service;
    public ExchangeRequestController(ExchangeRequestService service) {
        this.service = service;
    }

    @PostMapping("/create-request")
    public ExchangeRequest createExchangeRequest(@Validated @RequestBody ExchangeRequestDTO req){
        return service.createExchangeRequest(req);
    }

    @PutMapping("/update-request")
    public void updateExchangeRequest(@Validated @RequestBody UpdateExchangeRequestDTO req){
        service.updateExchangeRequest(req);
    }

    @GetMapping("/pending/{studentId}")
    public List<ExchangeRequest> getPendingRequests(@PathVariable Integer studentId){
        return service.getAllPendingRequests(studentId);
    }

    @GetMapping("/history/{studentId}")
    public  List<ExchangeRequest> getExchangeRequestHistory(@PathVariable Integer studentId){
        return service.getExchangeRequestHistory(studentId);
    }
}
