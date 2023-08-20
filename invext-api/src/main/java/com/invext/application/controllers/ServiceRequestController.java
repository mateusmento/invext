package com.invext.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.application.mapper.ServiceRequestMapper;
import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.services.ServiceRequestService;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private ServiceRequestMapper serviceRequestMapper;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping
    public ServiceRequestDto createServiceRequest(@RequestBody CreateServiceRequestDto dto) {
        var serviceRequest = serviceRequestService.createServiceRequest(dto);
        if (serviceRequest.getAttendant() != null)
            template.convertAndSend("/attendants/" + serviceRequest.getAttendant().getId(), serviceRequest);
        return serviceRequestMapper.toServiceRequestDto(serviceRequest);
    }

    @PutMapping("/{serviceRequestId}/finished")
    public void createServiceRequest(@PathVariable Long serviceRequestId) {
        var pair = serviceRequestService.finishServiceRequest(serviceRequestId);
        template.convertAndSend("/clients/" + pair.getFirst().getClientCode() + "/finished", pair.getFirst());
        pair.getSecond().ifPresent(serviceRequest -> {
            template.convertAndSend("/clients/" + serviceRequest.getClientCode() + "/accepted", serviceRequest);
        });
    }
}
