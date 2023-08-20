package com.invext.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invext.domain.dtos.CreateAttendantDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.services.AttendantService;

@RestController
@RequestMapping("/attendants")
public class AttendantController {

    @Autowired
    private AttendantService attendantService;

    @PostMapping
    public Attendant createAttendant(@RequestBody CreateAttendantDto dto) {
        return attendantService.createAttendant(dto);
    }

    @GetMapping("/{attendantId}/service-requests")
    public List<ServiceRequest> findAttendantServiceRequests(@PathVariable Long attendantId) {
        return attendantService.findAttendantServiceRequests(attendantId);
    }
}
