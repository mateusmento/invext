package com.invext.application.mapper;

import org.springframework.stereotype.Service;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

@Service
public class ServiceRequestMapper {

    public ServiceRequestDto toServiceRequestDto(ServiceRequest serviceRequest) {
        return ServiceRequestDto.builder()
            .id(serviceRequest.getId())
            .clientName(serviceRequest.getClientName())
            .clientCode(serviceRequest.getClientCode())
            .serviceType(serviceRequest.getServiceType())
            .attendant(serviceRequest.getAttendant())
            .build();
    }
}
