package com.invext.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.repositories.AttendantRepository;
import com.invext.domain.repositories.ServiceRequestRepository;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private AttendantRepository attendantRepository;

    @Autowired
    private ServiceRequestFactory serviceRequestFactory;

    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest createServiceRequest(CreateServiceRequestDto dto) {
        var serviceRequest = attendantRepository.findAvailableAttendant(dto.getServiceType())
            .map((attendant) -> serviceRequestFactory.acceptedServiceRequest(dto, attendant))
            .orElseGet(() -> serviceRequestFactory.pendingServiceRequest(dto));

        return serviceRequestRepository.save(serviceRequest);
    }
}
