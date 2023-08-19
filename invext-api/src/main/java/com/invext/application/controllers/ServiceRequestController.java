package com.invext.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.application.mapper.ServiceRequestMapper;
import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.services.ServiceRequestService;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private ServiceRequestMapper serviceRequestMapper;

    @GetMapping
    public List<ServiceRequest> findAll() {
        return serviceRequestService.findAll();
    }

    @PostMapping
    public ServiceRequestDto createServiceRequest(@RequestBody CreateServiceRequestDto dto) {
        var serviceRequest = serviceRequestService.createServiceRequest(dto);
        return serviceRequestMapper.toServiceRequestDto(serviceRequest);
    }
}
