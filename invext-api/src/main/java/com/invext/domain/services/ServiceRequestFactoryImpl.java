package com.invext.domain.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.values.ServiceRequestStatus;

@Service
public class ServiceRequestFactoryImpl implements ServiceRequestFactory {

    public ServiceRequest pendingServiceRequest(CreateServiceRequestDto dto) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .serviceType(dto.getServiceType())
            .status(ServiceRequestStatus.PENDING)
            .createdAt(LocalDateTime.now())
            .build();
    }

    public ServiceRequest acceptedServiceRequest(CreateServiceRequestDto dto, Attendant attendant) {
        return ServiceRequest.builder()
            .clientName(dto.getClientName())
            .serviceType(dto.getServiceType())
            .attendant(attendant)
            .status(ServiceRequestStatus.ACCEPTED)
            .createdAt(LocalDateTime.now())
            .build();
    }
}
