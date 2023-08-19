package com.invext.domain.services;

import org.springframework.stereotype.Service;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.values.ServiceRequestStatus;

@Service
public class ServiceRequestFactory {

    public ServiceRequest pendingServiceRequest(CreateServiceRequestDto dto) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .serviceType(dto.getServiceType())
            .status(ServiceRequestStatus.PENDING)
            .build();
    }

    public ServiceRequest acceptedServiceRequest(CreateServiceRequestDto dto, Attendant attendant) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .serviceType(dto.getServiceType())
            .attendant(attendant)
            .status(ServiceRequestStatus.ACCEPTED)
            .build();
    }
}
