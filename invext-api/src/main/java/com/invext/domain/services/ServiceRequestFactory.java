package com.invext.domain.services;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;

public interface ServiceRequestFactory {
    ServiceRequest pendingServiceRequest(CreateServiceRequestDto dto);
    ServiceRequest acceptedServiceRequest(CreateServiceRequestDto dto, Attendant attendant);
}
