package com.invext.application.mapper;

import org.springframework.stereotype.Service;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

@Service
public class ServiceRequestMapper {

    public ServiceRequest toServiceRequest(CreateServiceRequestDto dto) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .serviceType(dto.getServiceType())
            .build();
    }

    public ServiceRequestDto toServiceRequestDto(ServiceRequest serviceRequest) {
        return ServiceRequestDto.builder()
            .id(serviceRequest.getId())
            .clientName(serviceRequest.getClientName())
            .serviceType(serviceRequest.getServiceType())
            .attendant(serviceRequest.getAttendant())
            .build();
    }
}
