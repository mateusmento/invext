package com.invext.application.mapper;

import com.invext.application.dtos.ServiceRequestDto;
import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

public class ServiceRequestMapper {

    public ServiceRequest toServiceRequest(CreateServiceRequestDto dto) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .build();
    }

    public ServiceRequestDto toServiceRequestDto(ServiceRequest serviceRequest) {
        return ServiceRequestDto.builder()
            .id(serviceRequest.getId())
            .build();
    }
}
