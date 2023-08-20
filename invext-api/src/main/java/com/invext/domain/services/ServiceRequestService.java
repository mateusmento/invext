package com.invext.domain.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invext.domain.dtos.CreateServiceRequestDto;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.exceptions.InvalidServiceRequestFinishingException;
import com.invext.domain.exceptions.ServiceRequestNotFoundException;
import com.invext.domain.repositories.AttendantRepository;
import com.invext.domain.repositories.ServiceRequestRepository;
import com.invext.domain.values.ServiceRequestStatus;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private AttendantRepository attendantRepository;

    @Autowired
    private ServiceRequestFactory serviceRequestFactory;

    public ServiceRequest createServiceRequest(CreateServiceRequestDto dto) {
        var serviceRequest = attendantRepository.findAvailableAttendant(dto.getServiceType())
            .map((attendant) -> serviceRequestFactory.acceptedServiceRequest(dto, attendant))
            .orElseGet(() -> serviceRequestFactory.pendingServiceRequest(dto));
        return serviceRequestRepository.save(serviceRequest);
    }

    public void finishServiceRequest(Long serviceRequestId) {
        var serviceRequest = serviceRequestRepository.findById(serviceRequestId)
            .orElseThrow(ServiceRequestNotFoundException::new);
        if (!serviceRequest.getStatus().equals(ServiceRequestStatus.ACCEPTED))
            throw new InvalidServiceRequestFinishingException("Can not finish a not accepted ServiceRequest");
        serviceRequest.setStatus(ServiceRequestStatus.FINISHED);
        serviceRequestRepository.save(serviceRequest);
        serviceRequestRepository.findNextPendingServiceRequest(serviceRequest.getServiceType())
            .ifPresent((pendingServiceRequest) -> {
                pendingServiceRequest.setStatus(ServiceRequestStatus.ACCEPTED);
                pendingServiceRequest.setAttendant(serviceRequest.getAttendant());
                serviceRequestRepository.save(pendingServiceRequest);
            });
    }
}
