package com.invext.domain.services;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;

public interface ServiceRequestService {
    ServiceRequest createServiceRequest(CreateServiceRequestDto dto);
    void finishServiceRequest(Long serviceRequestId);
}
