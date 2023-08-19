package com.invext.application.mapper;

import com.invext.application.dtos.CreateServiceRequestDto;
import com.invext.application.dtos.ServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

public class ServiceRequestMapper {

    public ServiceRequest toServiceRequest(CreateServiceRequestDto dto) {
        return ServiceRequest.builder()
            .build();
    }

    public ServiceRequestDto toServiceRequestDto(ServiceRequest serviceRequest) {
        return ServiceRequestDto.builder()
            .id(serviceRequest.getId())
            .build();
    }
}
