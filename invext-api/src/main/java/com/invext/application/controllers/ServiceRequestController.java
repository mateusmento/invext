package com.invext.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.application.mapper.ServiceRequestMapper;
import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.services.ServiceRequestService;

@RestController("/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private ServiceRequestMapper serviceRequestMapper;

    @PostMapping
    public ServiceRequestDto createServiceRequest(@RequestBody CreateServiceRequestDto dto) {
        ServiceRequest serviceRequest = serviceRequestMapper.toServiceRequest(dto);
        serviceRequest = serviceRequestService.createServiceRequest(serviceRequest);
        return serviceRequestMapper.toServiceRequestDto(serviceRequest);
    }
}
