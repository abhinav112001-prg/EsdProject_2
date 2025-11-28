package com.example.esdProject_2.service;

import com.example.esdProject_2.dto.ExchangeRequestDTO;
import com.example.esdProject_2.dto.PendingRequestDTO;
import com.example.esdProject_2.dto.UpdateExchangeRequestDTO;
import com.example.esdProject_2.entity.ExchangeRequest;
import com.example.esdProject_2.entity.Hostel;
import com.example.esdProject_2.entity.Student;
import com.example.esdProject_2.exception.ResourceNotFoundException;
import com.example.esdProject_2.repos.ExchangeRequestRepo;
import com.example.esdProject_2.repos.HostelRepo;
import com.example.esdProject_2.repos.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ExchangeRequestService {
    private final ExchangeRequestRepo exchangeRequestRepo;
    private final StudentRepo studentRepo;
    private final HostelRepo hostelRepo;

    public ExchangeRequestService(ExchangeRequestRepo exchangeRequestRepo, StudentRepo studentRepo, HostelRepo hostelRepo) {
        this.exchangeRequestRepo = exchangeRequestRepo;
        this.studentRepo = studentRepo;
        this.hostelRepo = hostelRepo;
    }

    @Transactional
    public ExchangeRequest createExchangeRequest(ExchangeRequestDTO dto) {
        Student requester = studentRepo.findById(dto.getRequesterId())
                .orElseThrow(() -> new ResourceNotFoundException("Requester ID " + dto.getRequesterId() + " not found"));
        Student target = studentRepo.findById(dto.getTargetStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Target Student ID " + dto.getTargetStudentId() + " not found"));
        // need to check whether requester's is_request_active is false or true
        // if true, throw error
        if(requester.getIsRequestActive()) {
            throw new IllegalArgumentException("Requester already has an active request");
        }
        studentRepo.markRequestActive(requester.getStudent_id());

        ExchangeRequest req = new ExchangeRequest(requester, target);
        return exchangeRequestRepo.save(req);
    }

    @Transactional
    public void updateExchangeRequest(UpdateExchangeRequestDTO dto) {
        int id = dto.getRequestId();
        ExchangeRequest.ExchangeStatus status = dto.getStatus();
        ExchangeRequest req = exchangeRequestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exchange Request ID " + id + " not found"));
        // hostel table me: target id par jayega requester id
        // aur fir requester id par jayega target id
        Student requester = req.getRequester();
        Student target = req.getTarget_student();

        if (status == ExchangeRequest.ExchangeStatus.APPROVED) {
            // Dangerous logic
//            exchangeRequestRepo.updateExchangeRequest(dto);
//            hostelRepo.clearStudentByStudentId(requester_id);
//            hostelRepo.setStudentIdWhereStudentId(target_id, requester_id);
//            hostelRepo.setStudentIdWhereNull(target_id);
//            studentRepo.markRequestInactive(requester_id);

            Hostel requesterRoom = hostelRepo.findByStudentId(requester.getStudent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Requester does not have a room assigned"));
            Hostel targetRoom = hostelRepo.findByStudentId(target.getStudent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Target does not have a room assigned"));

            // 2. The Swap Logic (Safe JPA approach)
            // Phase 1: Unlink both rooms to avoid unique key collisions
            requesterRoom.setStudent(null);
            targetRoom.setStudent(null);
            hostelRepo.saveAndFlush(requesterRoom);
            hostelRepo.saveAndFlush(targetRoom);

            // Phase 2: Assign swapped occupants
            requesterRoom.setStudent(target);
            targetRoom.setStudent(requester);
            hostelRepo.saveAndFlush(targetRoom);
            hostelRepo.saveAndFlush(requesterRoom);

            // 3. Update Request Status & Unlock Student
            exchangeRequestRepo.updateExchangeRequest(dto);
            studentRepo.markRequestInactive(requester.getStudent_id());
        }
        else if (status == ExchangeRequest.ExchangeStatus.REJECTED) {
            exchangeRequestRepo.updateExchangeRequest(dto);
            studentRepo.markRequestInactive(requester.getStudent_id());
        }
        else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

    }
    public List<ExchangeRequest> getAllPendingRequests(Integer studentId){
        return exchangeRequestRepo.findAllByTargetId(studentId);
    }
    public List<ExchangeRequest> getExchangeRequestHistory(Integer studentId) {
        return exchangeRequestRepo.findAllByRequesterId(studentId);
    }
}
