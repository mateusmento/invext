package com.invext.domain.services;

import java.util.Optional;

import org.springframework.data.util.Pair;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

public interface ServiceRequestService {
    ServiceRequest createServiceRequest(CreateServiceRequestDto dto);
    Pair<ServiceRequest, Optional<ServiceRequest>> finishServiceRequest(Long serviceRequestId);
}
